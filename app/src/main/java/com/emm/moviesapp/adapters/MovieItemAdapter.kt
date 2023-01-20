package com.emm.moviesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emm.moviesapp.databinding.ItemMovieBinding
import com.emm.moviesapp.model.MovieUI

class MovieItemAdapter(
    private val onItemClick: (MovieUI) -> Unit
) : ListAdapter<MovieUI, MovieItemAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class ViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieUI) = with(binding) {
            this.movie = movie
            root.setOnClickListener { onItemClick(movie) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieUI>() {
            override fun areItemsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean =
                oldItem == newItem
        }
    }
}