package com.jccword.cvreader.cv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jccword.cvreader.R
import com.jccword.cvreader.domain.Work
import com.jccword.cvreader.service.CVService
import com.jccword.cvreader.ui.NotificationUi
import com.jccword.cvreader.ui.ProgressUi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CVViewModel(cvService: CVService, progressUi: ProgressUi, notificationUi: NotificationUi): ViewModel() {
    private val subscriptions = CompositeDisposable()

    val name = MutableLiveData<String>()
    val label = MutableLiveData<String>()
    val picture = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val website = MutableLiveData<String>()
    val summary = MutableLiveData<String>()

    var work = MutableLiveData<List<Work>>()

    init {
        subscriptions.add(cvService.getCV()
                .doOnSubscribe { progressUi.showProgress() }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ cv ->
                        cv.basics?.let { cv ->
                            name.value = cv.name
                            label.value = cv.label
                            picture.value = cv.picture
                            email.value = cv.email
                            phone.value = cv.phone
                            website.value = cv.website
                            summary.value = cv.summary
                        }
                        cv.work?.let {
                            work.value = it
                        }

                        progressUi.hideProgress()
                    },
                    { t -> notificationUi.showMessage(R.string.network_error) }


                ))
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}
