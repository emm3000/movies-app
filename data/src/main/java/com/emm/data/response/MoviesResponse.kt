package com.emm.data.response

data class MoviesResponse(
    val errorMessage: String,
    val items: List<MovieResponse>
)

data class MovieResponse(
    val contentRating: String,
    val directorList: List<DirectorResponse>,
    val directors: String,
    val fullTitle: String,
    val genreList: List<GenreResponse>,
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
    val starList: List<StarResponse>,
    val stars: String,
    val title: String,
    val year: String
)

data class DirectorResponse(
    val id: String,
    val name: String
)

data class GenreResponse(
    val key: String,
    val value: String
)

data class StarResponse(
    val id: String,
    val name: String
)