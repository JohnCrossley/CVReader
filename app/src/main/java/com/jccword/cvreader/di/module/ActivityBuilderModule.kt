package com.jccword.cvreader.di.module

import com.jccword.cvreader.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, NavigationModule::class, ViewModelModule::class, UiModule::class, MainActivityFragmentBuilderModule::class])
    internal abstract fun mainActivity(): MainActivity

}
