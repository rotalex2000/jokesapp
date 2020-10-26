package com.alexrotariu.jokesapp.data.network.mappers

import com.alexrotariu.jokesapp.data.network.models.JokeDTO
import com.alexrotariu.jokesapp.domain.models.Joke
import javax.inject.Inject

class JokeMapper @Inject constructor(
    private val flagsMapper: FlagsMapper
) {

    fun map(jokeDTO: JokeDTO): Joke {
        return Joke(
            error = jokeDTO.error,
            category = jokeDTO.category,
            type = jokeDTO.type,
            joke = jokeDTO.joke,
            flags = flagsMapper.map(jokeDTO.flags),
            id = jokeDTO.id,
            lang = jokeDTO.lang
        )
    }
}