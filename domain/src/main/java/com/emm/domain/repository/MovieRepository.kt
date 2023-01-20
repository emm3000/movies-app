package com.emm.domain.repository

import com.emm.core.Result
import com.emm.domain.entities.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun loadMoviesList(): Flow<Result<List<MovieModel>>>

    fun getMovieById(movieId: String): Flow<Result<MovieModel>>

}