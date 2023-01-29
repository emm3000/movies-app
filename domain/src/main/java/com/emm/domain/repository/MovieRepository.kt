package com.emm.domain.repository

import com.emm.core.Result
import com.emm.domain.entities.MovieModel
import com.emm.domain.entities.MovieWithSimilarGenresModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun loadMoviesList(): Flow<Result<List<MovieModel>>>

    fun getMovieByIdWithSimilarGenres(movieId: String): Flow<Result<MovieWithSimilarGenresModel>>
}
