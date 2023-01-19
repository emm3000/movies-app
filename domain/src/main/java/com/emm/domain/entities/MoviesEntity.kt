package com.emm.domain.entities

data class MoviesEntity(
    val errorMessage: String,
    val items: List<MovieEntity>
)

data class MovieEntity(
    val contentRating: String,
    val directorList: List<DirectorEntity>,
    val directors: String,
    val fullTitle: String,
    val genreList: List<GenreEntity>,
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
    val starList: List<StarEntity>,
    val stars: String,
    val title: String,
    val year: String
)

data class DirectorEntity(
    val id: String,
    val name: String
)

data class GenreEntity(
    val key: String,
    val value: String
)

data class StarEntity(
    val id: String,
    val name: String
)