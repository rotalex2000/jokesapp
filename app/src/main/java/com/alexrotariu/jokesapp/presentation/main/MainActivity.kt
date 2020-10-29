package com.alexrotariu.jokesapp.presentation.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexrotariu.jokesapp.R
import com.alexrotariu.jokesapp.di.JokesApp
import com.alexrotariu.jokesapp.domain.models.Categories
import com.alexrotariu.jokesapp.domain.models.Jokes
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.dialog_filter_jokes.view.*
import javax.inject.Inject
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private val categories = Categories.values().map { it.name }.toTypedArray()

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as JokesApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

//        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
//            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
//                // Collapsed
//            } else {
//                // Expanded
//            }
//        })

        setOnClickListeners()
        setupObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_refresh) {
            mainViewModel.loadJokes(
                mainViewModel.filters[0],
                mainViewModel.filters[1],
                mainViewModel.filters[2],
                mainViewModel.filters[3],
                mainViewModel.filters[4],
                mainViewModel.filters[5],
                mainViewModel.filters[6],
                mainViewModel.filters[7]
            )
            return true
        }

        return super.onOptionsItemSelected(item)
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

            mainViewModel.filters[0] = categories
            mainViewModel.filters[7] = amount

            mainViewModel.loadJokes(
                mainViewModel.filters[0],
                mainViewModel.filters[1],
                mainViewModel.filters[2],
                mainViewModel.filters[3],
                mainViewModel.filters[4],
                mainViewModel.filters[5],
                mainViewModel.filters[6],
                mainViewModel.filters[7]
            )
        }

    }
}