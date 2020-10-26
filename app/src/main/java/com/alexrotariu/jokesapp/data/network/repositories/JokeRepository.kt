package com.alexrotariu.jokesapp.data.network.repositories

import com.alexrotariu.jokesapp.data.network.APIClient
import com.alexrotariu.jokesapp.data.network.remotedatasources.JokeRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokeRepository @Inject constructor(
    private val jokeRemoteDataSource: JokeRemoteDataSource
) {

    suspend fun getJokesRemote(categories: String, amount: String) = jokeRemoteDataSource.getJokes(categories, amount = amount)
}