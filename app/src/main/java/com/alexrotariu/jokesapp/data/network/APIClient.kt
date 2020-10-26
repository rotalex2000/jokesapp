package com.alexrotariu.jokesapp.data.network

import com.alexrotariu.jokesapp.data.network.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class APIClient private constructor() {
    companion object {
        val instance = APIClient()
    }

    private val okkHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okkHttpClient)
            .build()
    }
}