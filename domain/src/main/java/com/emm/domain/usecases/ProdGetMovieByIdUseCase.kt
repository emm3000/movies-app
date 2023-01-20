package com.emm.domain.usecases

import com.emm.core.Result
import com.emm.domain.entities.MovieModel
import com.emm.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProdGetMovieByIdUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : GetMovieByIdUseCase {

    override fun invoke(id: String): Flow<Result<MovieModel>> {
        return movieRepository.getMovieById(id)
    }

}