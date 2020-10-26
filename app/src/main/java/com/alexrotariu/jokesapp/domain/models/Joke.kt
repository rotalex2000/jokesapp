package com.alexrotariu.jokesapp.domain.models

data class Joke (
    var error: Boolean = false,
    var category: String,
    var type: String,
    var joke: String,
    var flags: Flags,
    var id: Int,
    var lang: String
)