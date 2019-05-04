package com.jccword.cvreader.di.module

import android.content.Context
import com.jccword.cvreader.MainActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun bindContext(mainActivity: MainActivity): Context

}
