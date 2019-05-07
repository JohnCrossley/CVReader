package com.jccword.cvreader.di.component

import com.jccword.cvreader.Application
import com.jccword.cvreader.MainActivity
import com.jccword.cvreader.di.InjectableModelViewFactory
import com.jccword.cvreader.di.module.NavigationModule
import com.jccword.cvreader.di.module.UiModule
import com.jccword.cvreader.di.module.WebService
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

@Component(modules = [ WebService::class, NavigationModule::class, UiModule::class ])
interface InjectableModelViewFactoryComponent : AndroidInjector<InjectableModelViewFactory> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun mainActivity(mainActivity: MainActivity): Builder

        fun build(): InjectableModelViewFactoryComponent

    }
}
