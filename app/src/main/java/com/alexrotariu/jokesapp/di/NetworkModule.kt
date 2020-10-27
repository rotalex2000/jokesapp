package com.alexrotariu.jokesapp.di

import com.alexrotariu.jokesapp.data.network.APIClient
import com.alexrotariu.jokesapp.data.network.apiservices.JokesAPIService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): Retrofit {
        return APIClient.instance.retrofit
    }
}