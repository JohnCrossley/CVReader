package com.jccword.cvreader.di.module

import com.jccword.cvreader.Application
import com.jccword.cvreader.MainActivity
import com.jccword.cvreader.di.InjectableModelViewFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    internal fun provideInjectableViewModelFactory(application: Application, mainActivity: MainActivity): InjectableModelViewFactory {
        return InjectableModelViewFactory(application, mainActivity)
    }
}
