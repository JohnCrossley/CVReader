package com.jccword.cvreader.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jccword.cvreader.Application
import com.jccword.cvreader.MainActivity
import com.jccword.cvreader.cv.CVViewModel
import com.jccword.cvreader.di.component.DaggerInjectableModelViewFactoryComponent
import com.jccword.cvreader.service.CVService
import javax.inject.Inject

class InjectableModelViewFactory(application: Application, mainActivity: MainActivity) : ViewModelProvider.Factory {
    @Inject
    lateinit var cvService: CVService

    init {
        DaggerInjectableModelViewFactoryComponent.builder()
                .application(application)
                .mainActivity(mainActivity)
                .build()
                .inject(this)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CVViewModel::class.java) -> CVViewModel(cvService) as T
            else -> throw IllegalArgumentException("Unknown ViewModel: $modelClass")
        }
    }

}
