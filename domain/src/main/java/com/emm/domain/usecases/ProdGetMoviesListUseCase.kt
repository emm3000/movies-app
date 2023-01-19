package com.emm.domain.usecases

import com.emm.core.Result
import com.emm.domain.repository.MovieRepository
import com.emm.domain.entities.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProdGetMoviesListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : GetMoviesListUseCase {

    override fun invoke(): Flow<Result<List<MovieEntity>>> {
        return movieRepository.getMoviesList()
    }

}