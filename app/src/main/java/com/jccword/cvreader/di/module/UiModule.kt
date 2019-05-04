package com.jccword.cvreader.di.module

import com.jccword.cvreader.MainActivity
import com.jccword.cvreader.ui.ProgressUi
import com.jccword.cvreader.ui.NotificationUi
import dagger.Binds
import dagger.Module

@Module
interface UiModule {

    @Binds
    fun bindsProgressUi(mainActivity: MainActivity): ProgressUi

    @Binds
    fun bindsShowMessage(mainActivity: MainActivity): NotificationUi

}
