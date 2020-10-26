package com.alexrotariu.jokesapp.domain.models

data class Flags (
    var nsfw: Boolean,
    var religious: Boolean,
    var political: Boolean,
    var racist: Boolean,
    var sexist: Boolean
)