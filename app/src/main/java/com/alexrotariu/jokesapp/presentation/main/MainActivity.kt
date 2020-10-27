package com.alexrotariu.jokesapp.presentation.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexrotariu.jokesapp.R
import com.alexrotariu.jokesapp.di.JokesApp
import com.alexrotariu.jokesapp.domain.models.Categories
import com.alexrotariu.jokesapp.domain.models.Jokes
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_filter_jokes.view.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private val categories = Categories.values().map { it.name }.toTypedArray()

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as JokesApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnClickListeners()
        setupObservers()
    }

    private fun setupObservers() {
        mainViewModel.getJokes().observe(this, Observer { newJokes ->
            setupRecyclerView(newJokes)
        })
    }

    private fun setOnClickListeners() {
        btnFilter.setOnClickListener {
            openFilterDialog()
        }
    }

    private fun setupRecyclerView(jokesObject: Jokes) {
        val jokes = jokesObject.jokes
        rvJokes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvJokes.adapter = JokesAdapter(jokes)
    }

    private fun openFilterDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_filter_jokes, null)

        val builder = AlertDialog.Builder(this)
            .setView(dialogView)

        dialogView.npCategory.minValue = 0
        dialogView.npCategory.maxValue = categories.size - 1
        dialogView.npCategory.displayedValues = categories

        dialogView.npAmount.minValue = 1
        dialogView.npAmount.maxValue = 10

        val alertDialog = builder.show()

        dialogView.btnApplyFilters.setOnClickListener {
            alertDialog.dismiss()
            val categories = categories[dialogView.npCategory.value]
            val amount = dialogView.npAmount.value.toString()
            mainViewModel.loadJokes(
                categories,
                "en",
                "",
                "json",
                "single",
                "",
                "",
                amount
            )
        }

    }
}