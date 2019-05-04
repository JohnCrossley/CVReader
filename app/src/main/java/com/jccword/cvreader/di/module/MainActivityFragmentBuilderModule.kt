package com.jccword.cvreader.di.module

import com.jccword.cvreader.what.CVFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun dashboardFragment(): CVFragment

}
