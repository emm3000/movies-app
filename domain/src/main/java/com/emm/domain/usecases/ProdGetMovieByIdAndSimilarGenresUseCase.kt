package com.emm.domain.usecases

import com.emm.core.Result
import com.emm.domain.entities.MovieWithSimilarGenresModel
import com.emm.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProdGetMovieByIdAndSimilarGenresUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) : GetMovieByIdAndSimilarGenresUseCase {

    override fun invoke(movieID: String): Flow<Result<MovieWithSimilarGenresModel>> {
        return movieRepository.getMovieByIdWithSimilarGenres(movieID)
    }
}
