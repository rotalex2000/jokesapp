package com.alexrotariu.jokesapp.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alexrotariu.jokesapp.R
import com.alexrotariu.jokesapp.di.JokesApp
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

//    private val mainViewModel: MainViewModel by viewModels()
    @Inject lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as JokesApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupObservers()
    }

    private fun setupObservers() {
        mainViewModel.getJokes().observe(this, Observer { newJokes ->
            randomJoke.text = newJokes.jokes[0].joke
        })
    }
}