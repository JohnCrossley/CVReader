package com.jccword.cvreader.di.module

import com.jccword.cvreader.MainActivity
import com.jccword.cvreader.ui.Navigation
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @Provides
    fun provideNavigationCallbacks(mainActivity: MainActivity) = Navigation(mainActivity.supportFragmentManager)

}
