package com.alexrotariu.jokesapp.domain.models

enum class BlacklistFlags(name: String) {
    NSFW("nsfw"),
    RELIGIOUS("religious"),
    POLITICAL("political"),
    RACIST("racist"),
    SEXIST("sexist")
}