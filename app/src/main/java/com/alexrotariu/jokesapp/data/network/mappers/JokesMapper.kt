package com.alexrotariu.jokesapp.data.network.mappers

import com.alexrotariu.jokesapp.data.network.models.JokesDTO
import com.alexrotariu.jokesapp.domain.models.Joke
import com.alexrotariu.jokesapp.domain.models.Jokes
import javax.inject.Inject

class JokesMapper @Inject constructor(
    private val jokeMapper: JokeMapper
) {

    fun map(jokesDTO: JokesDTO): Jokes {
        return Jokes(
            error = jokesDTO.error,
            jokes = jokesDTO.jokes.map { jokeMapper.map(it) },
            amount = jokesDTO.amount
        )
    }
}