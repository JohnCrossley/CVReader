package com.jccword.cvreader.cv

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jccword.cvreader.domain.Skill
import com.jccword.cvreader.domain.Work
import com.jccword.cvreader.service.CVService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CVViewModel(cvService: CVService): ViewModel() {
    private val subscriptions = CompositeDisposable()

    val state = MutableLiveData<State>()

    val name = MutableLiveData<String>()
    val label = MutableLiveData<String>()
    val picture = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val website = MutableLiveData<String>()
    val summary = MutableLiveData<String>()

    var work = MutableLiveData<List<Work>>()

    var skills = MutableLiveData<String>()

    init {
        subscriptions.add(cvService.getCV()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { state.value = State.LOADING }
                .subscribe({ cv ->
                        cv.basics.let { basics ->
                            name.value = basics.name
                            label.value = basics.label
                            picture.value = basics.picture
                            email.value = basics.email
                            phone.value = basics.phone
                            website.value = basics.website
                            summary.value = basics.summary
                        }

                        cv.work.let {
                            work.value = it
                        }

                        skills.value = cv.skills.toBulletList()

                        state.value = State.READY
                    },
                    {
                        Log.e(TAG, it.message, it)
                        state.value = State.ERROR
                    }
                )
            )
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

    companion object {
        val TAG = "CVViewModel"
    }
}

private fun List<Skill>.toBulletList(): String {
    val s = "\u2022 %s (%s)\n"
    val sb = StringBuilder()
    for(skill in this) {
        sb.append(String.format(s, skill.name, skill.level))
    }
    return sb.toString()
}


