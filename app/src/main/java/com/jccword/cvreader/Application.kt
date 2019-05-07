package com.jccword.cvreader

import com.jccword.cvreader.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class Application : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerApplicationComponent.builder().app(this).build()
        component.inject(this)
        return component
    }
}
