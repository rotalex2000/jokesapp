package com.alexrotariu.jokesapp.domain.usecases

import com.alexrotariu.jokesapp.domain.models.Jokes
import com.alexrotariu.jokesapp.data.network.repositories.JokeRepository
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetJokesUseCase @Inject constructor(private val jokeRepository: JokeRepository) {

    fun run(categories: String, amount: String, onResult: (Jokes) -> Unit = {}): Job {
        val job = GlobalScope.async(Dispatchers.IO) {
            jokeRepository.getJokesRemote(categories, amount)
        }
        GlobalScope.launch(Dispatchers.Main) {
            onResult(job.await())
        }
        return job
    }
}