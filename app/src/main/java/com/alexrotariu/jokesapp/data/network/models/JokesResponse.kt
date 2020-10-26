package com.alexrotariu.jokesapp.data.network.models

import com.alexrotariu.jokesapp.domain.models.Flags
import com.alexrotariu.jokesapp.domain.models.Joke
import com.squareup.moshi.JsonClass

sealed class JokesResponse
@JsonClass(generateAdapter = true)
data class JokeDTO (
    var error: Boolean = false,
    var category: String,
    var type: String,
    var joke: String,
    var flags: FlagsDTO,
    var id: Int,
    var lang: String
) : JokesResponse()
@JsonClass(generateAdapter = true)
data class JokesDTO (
    var error: Boolean,
    var jokes: List<JokeDTO>,
    var amount: Int
) : JokesResponse()