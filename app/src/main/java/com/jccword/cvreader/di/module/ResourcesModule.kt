package com.jccword.cvreader.di.module

import com.jccword.cvreader.MainActivity
import dagger.Module
import dagger.Provides

@Module
class ResourcesModule {

    @Provides
    internal fun provideResources(mainActivity: MainActivity) = mainActivity.resources

}
