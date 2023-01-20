package com.emm.data.api.response

data class MoviesResponse(
    val errorMessage: String,
    val items: List<MovieResponse>
)

data class MovieResponse(
    val contentRating: String,
    val directors: String,
    val fullTitle: String,
    val genres: String,
    val id: String,
    val image: String,
    val plot: String,
    val releaseState: String,
    val stars: String,
    val title: String,
    val year: String
)