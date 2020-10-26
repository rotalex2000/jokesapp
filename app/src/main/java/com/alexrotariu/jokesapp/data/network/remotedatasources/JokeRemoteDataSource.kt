package com.alexrotariu.jokesapp.data.network.remotedatasources

import com.alexrotariu.jokesapp.data.network.apiservices.JokesAPIService
import com.alexrotariu.jokesapp.data.network.executeAndDeliver
import com.alexrotariu.jokesapp.data.network.mappers.JokeMapper
import com.alexrotariu.jokesapp.data.network.mappers.JokesMapper
import com.alexrotariu.jokesapp.data.network.models.JokesDTO
import com.alexrotariu.jokesapp.domain.models.Joke
import com.alexrotariu.jokesapp.domain.models.Jokes
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokeRemoteDataSource @Inject constructor(
    retrofit: Retrofit,
    private val jokesMapper: JokesMapper
) {
    private val jokesAPIService: JokesAPIService = retrofit.create(JokesAPIService::class.java)

    fun getJokes(categories: String, amount: String): Jokes {
        return if (amount == "1") {
            val response = jokesAPIService.getJoke(categories).executeAndDeliver()
            val jokes = JokesDTO(
                response.error,
                listOf(response),
                amount.toInt()
            )
            jokesMapper.map(jokes)
        } else {
            val response = jokesAPIService.getJokes(categories, amount = amount).executeAndDeliver()
            jokesMapper.map(response)
        }
    }
}