package com.alexrotariu.jokesapp.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexrotariu.jokesapp.R
import com.alexrotariu.jokesapp.di.JokesApp
import com.alexrotariu.jokesapp.domain.models.Jokes
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

//    private val mainViewModel: MainViewModel by viewModels()
    @Inject lateinit var mainViewModel: MainViewModel

    private var jokesAdapter: JokesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as JokesApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupObservers()
    }

    private fun setupObservers() {
        mainViewModel.getJokes().observe(this, Observer { newJokes ->
            setupRecyclerView(newJokes)
        })
    }

    private fun setupRecyclerView(jokesObject: Jokes) {
        val jokes = jokesObject.jokes
        rvJokes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvJokes.adapter = JokesAdapter(jokes)
    }
}