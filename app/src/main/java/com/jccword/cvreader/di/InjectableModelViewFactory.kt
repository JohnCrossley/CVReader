package com.jccword.cvreader.di

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jccword.cvreader.Application
import com.jccword.cvreader.service.CVService
import com.jccword.cvreader.MainActivity
import com.jccword.cvreader.MainActivityViewModel
import com.jccword.cvreader.ui.Navigation
import com.jccword.cvreader.cv.CVViewModel
import com.jccword.cvreader.di.component.DaggerInjectableModelViewFactoryComponent
import com.jccword.cvreader.ui.NotificationUi
import com.jccword.cvreader.ui.ProgressUi
import javax.inject.Inject

class InjectableModelViewFactory(application: Application, mainActivity: MainActivity) : ViewModelProvider.Factory {
    @Inject
    lateinit var cvService: CVService

    @Inject
    lateinit var navigation: Navigation

    @Inject
    lateinit var progressUi: ProgressUi

    @Inject
    lateinit var notificationUi: NotificationUi

    @Inject
    lateinit var resources: Resources

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
            modelClass.isAssignableFrom(MainActivityViewModel::class.java) -> MainActivityViewModel(navigation) as T
            modelClass.isAssignableFrom(CVViewModel::class.java) -> CVViewModel(cvService, progressUi, notificationUi, resources) as T
            else -> throw IllegalArgumentException("Unknown ViewModel: $modelClass")
        }
    }

}
