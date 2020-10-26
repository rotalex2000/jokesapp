package com.alexrotariu.jokesapp.domain.models

data class Jokes (
    var error: Boolean,
    var jokes: List<Joke>,
    var amount: Int
)