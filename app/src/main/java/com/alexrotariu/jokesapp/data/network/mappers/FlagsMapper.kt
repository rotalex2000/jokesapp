package com.alexrotariu.jokesapp.data.network.mappers

import com.alexrotariu.jokesapp.data.network.models.FlagsDTO
import com.alexrotariu.jokesapp.domain.models.Flags
import javax.inject.Inject

class FlagsMapper @Inject constructor() {

    fun map(flagsDTO: FlagsDTO): Flags {
        return Flags(
            nsfw = flagsDTO.nsfw,
            religious = flagsDTO.religious,
            political = flagsDTO.political,
            racist = flagsDTO.racist,
            sexist = flagsDTO.sexist
        )
    }
}