package com.alexrotariu.jokesapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexrotariu.jokesapp.data.network.repositories.JokeRepository
import com.alexrotariu.jokesapp.domain.models.Jokes
import com.alexrotariu.jokesapp.domain.usecases.GetJokesUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getJokesUseCase: GetJokesUseCase
) {

    private val _jokes: MutableLiveData<Jokes> = MutableLiveData()
    fun getJokes(): LiveData<Jokes> = _jokes

    init {
        loadJokes(
            "Any",
            "en",
            "",
            "json",
            "single",
            "",
            "",
            "4"
        )
    }

    private fun loadJokes(
        categories: String,
        lang: String,
        blacklistFlags: String,
        format: String,
        type: String,
        contains: String,
        idRange: String,
        amount: String
    ) {
        getJokesUseCase.run(
            categories,
            lang,
            blacklistFlags,
            format,
            type,
            contains,
            idRange,
            amount,
            ::onLoadJokesReady
        )
    }

    private fun onLoadJokesReady(jokesObject: Jokes) {
        _jokes.value = jokesObject
    }
}