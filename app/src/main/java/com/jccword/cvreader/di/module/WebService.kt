package com.jccword.cvreader.di.module

import com.jccword.cvreader.service.CVService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class WebService {
    @Provides
    internal fun provideRetrofit() = Retrofit.Builder()
            .baseUrl(CVService.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    internal fun provideCVService(retrofit: Retrofit) = retrofit.create<CVService>(CVService::class.java)

}