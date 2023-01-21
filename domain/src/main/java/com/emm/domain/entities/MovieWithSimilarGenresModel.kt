package com.emm.domain.entities

data class MovieWithSimilarGenresModel(
    val movie: MovieModel,
    val similarGenres: List<MovieModel>
)
