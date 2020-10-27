package com.alexrotariu.jokesapp.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexrotariu.jokesapp.R
import com.alexrotariu.jokesapp.domain.models.Joke
import kotlinx.android.synthetic.main.item_jokes.view.*

class JokesAdapter(private val jokesList: List<Joke>) : RecyclerView.Adapter<JokesAdapter.JokeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesAdapter.JokeViewHolder {
        return JokeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_jokes, parent, false))
    }

    override fun onBindViewHolder(holder: JokesAdapter.JokeViewHolder, position: Int) {
        val joke: Joke? = jokesList[position]
        joke?.let {
            holder.bind(joke)
        }
    }

    override fun getItemCount(): Int {
        return jokesList.size
    }

    inner class JokeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(joke: Joke) {
            itemView.tvJoke.text = joke.joke
            itemView.mchvCategory.text = joke.category

            if (joke.flags.nsfw) itemView.mchvFlagNSFW.visibility = View.VISIBLE
            if (joke.flags.religious) itemView.mchvFlagRel.visibility = View.VISIBLE
            if (joke.flags.political) itemView.mchvFlagPol.visibility = View.VISIBLE
            if (joke.flags.racist) itemView.mchvFlagRac.visibility = View.VISIBLE
            if (joke.flags.sexist) itemView.mchvFlagSex.visibility = View.VISIBLE

        }
    }
}