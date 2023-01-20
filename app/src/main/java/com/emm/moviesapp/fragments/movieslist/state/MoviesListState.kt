package com.emm.moviesapp.fragments.movieslist.state

import com.emm.moviesapp.model.MovieModel

data class MoviesListState(
    val moviesList: List<MovieModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
