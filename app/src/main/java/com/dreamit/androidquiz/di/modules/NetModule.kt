package com.dreamit.androidquiz.di.modules

import com.dreamit.androidquiz.net.RestClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetModule(private val baseUrl: String) {

    @Provides
    @Singleton
    fun provideQuizService() = RestClient(baseUrl).setupRestClient()
}