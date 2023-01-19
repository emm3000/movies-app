package com.emm.domain.usecases

import com.emm.core.Result
import com.emm.domain.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface GetMoviesListUseCase {

    operator fun invoke(): Flow<Result<List<MovieEntity>>>

}