package com.emm.domain.usecases

import com.emm.core.Result
import com.emm.domain.entities.MovieModel
import kotlinx.coroutines.flow.Flow

interface GetMovieByIdUseCase {

    operator fun invoke(id: String): Flow<Result<MovieModel>>

}