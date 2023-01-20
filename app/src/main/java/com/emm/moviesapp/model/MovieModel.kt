package com.emm.moviesapp.model

data class MovieModel(
    val contentRating: String,
    val directorList: List<DirectorModel>,
    val directors: String,
    val fullTitle: String,
    val genreList: List<GenreModel>,
    val genres: String,
    val id: String,
    val imDbRating: String,
    val imDbRatingCount: String,
    val image: String,
    val metacriticRating: String,
    val plot: String,
    val releaseState: String,
    val runtimeStr: String,
    val runtimeMins: String,
    val starList: List<StarModel>,
    val stars: String,
    val title: String,
    val year: String
)

data class DirectorModel(
    val id: String,
    val name: String
)

data class GenreModel(
    val key: String,
    val value: String
)

data class StarModel(
    val id: String,
    val name: String
)
