package com.emm.data.repository

import com.emm.core.Result
import com.emm.core.mapper
import com.emm.data.datasource.RemoteMovieDataSource
import com.emm.data.mapper.MovieDataMapper
import com.emm.domain.entities.MovieEntity
import com.emm.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDataSource: RemoteMovieDataSource,
    private val movieDataMapper: MovieDataMapper
) : MovieRepository {

    override fun getMoviesList(): Flow<Result<List<MovieEntity>>> = flow {
        val movieListResult = movieDataSource.getMoviesList().mapper {
            it.items.map(movieDataMapper::mapMovieListResponseToDomainEntity)
        }

        emit(movieListResult)
    }

}