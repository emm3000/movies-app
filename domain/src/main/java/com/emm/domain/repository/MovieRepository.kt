package com.emm.domain.repository

import com.emm.core.Result
import com.emm.domain.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviesList(): Flow<Result<List<MovieEntity>>>

}