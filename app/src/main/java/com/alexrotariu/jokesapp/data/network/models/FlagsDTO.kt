package com.alexrotariu.jokesapp.data.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlagsDTO (
    var nsfw: Boolean,
    var religious: Boolean,
    var political: Boolean,
    var racist: Boolean,
    var sexist: Boolean
)