package com.emm.domain.usecases

import com.emm.core.Result
import com.emm.domain.entities.MovieWithSimilarGenresModel
import kotlinx.coroutines.flow.Flow

interface GetMovieByIdAndSimilarGenresUseCase {

    operator fun invoke(movieID: String): Flow<Result<MovieWithSimilarGenresModel>>
}
