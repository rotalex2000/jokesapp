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
        loadJokes("Any", "1")
    }

    private fun loadJokes(categories: String, amount: String) {
        getJokesUseCase.run(categories, amount, ::onLoadJokesReady)
    }

    private fun onLoadJokesReady(jokesObject: Jokes) {
        _jokes.value = jokesObject
    }
}