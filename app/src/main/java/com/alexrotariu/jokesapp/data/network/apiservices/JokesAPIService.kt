package com.alexrotariu.jokesapp.data.network.apiservices

import com.alexrotariu.jokesapp.data.network.models.JokeDTO
import com.alexrotariu.jokesapp.data.network.models.JokesDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JokesAPIService {

    @GET("joke/{categories}")
    fun getJoke(
        @Path("categories") categories: String = "Any",
        @Query("lang") lang: String = "en",
        @Query("blacklistFlags") blacklistFlags: String = "",
        @Query("format") format: String = "json",
        @Query("type") type: String = "single",
        @Query("contains") contains: String = "",
        @Query("idRange") idRange: String = "",
        @Query("amount") amount: String = "1"
    ): Call<JokeDTO>

    @GET("joke/{categories}")
    fun getJokes(
        @Path("categories") categories: String = "Any",
        @Query("lang") lang: String = "en",
        @Query("blacklistFlags") blacklistFlags: String = "",
        @Query("format") format: String = "json",
        @Query("type") type: String = "single",
        @Query("contains") contains: String = "",
        @Query("idRange") idRange: String = "",
        @Query("amount") amount: String
    ): Call<JokesDTO>
}