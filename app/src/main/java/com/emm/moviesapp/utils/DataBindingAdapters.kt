package com.emm.moviesapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.emm.moviesapp.adapters.MovieItemAdapter
import com.emm.moviesapp.model.MovieUI

const val CROSS_FADE_DURATION = 1000

@BindingAdapter("app:url")
fun setImageUrl(view: ImageView, url: String?) {
    url?.let {
        view.load(it) {
            crossfade(true)
            crossfade(CROSS_FADE_DURATION)
        }
    }
}

@BindingAdapter("app:submitList")
fun setMovieList(rv: RecyclerView, items: List<MovieUI>?) {
    items?.let {
        (rv.adapter as? MovieItemAdapter)?.submitList(it)
    }
}
