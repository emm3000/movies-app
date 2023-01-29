package com.emm.moviesapp.fragments.moviedetail.state

import com.emm.moviesapp.model.MovieUI

data class MovieDetailState(
    val currentMovie: MovieUI? = null,
    val similarGenres: List<MovieUI> = emptyList(),
    val similarDirectors: List<MovieUI> = emptyList(),
    val similarStars: List<MovieUI> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
