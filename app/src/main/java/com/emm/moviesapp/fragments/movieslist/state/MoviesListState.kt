package com.emm.moviesapp.fragments.movieslist.state

import com.emm.moviesapp.model.MovieUI

data class MoviesListState(
    val moviesList: List<MovieUI> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
