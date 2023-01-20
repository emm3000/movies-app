package com.emm.data.repository

import com.emm.core.Result
import com.emm.core.mapper
import com.emm.data.api.response.MoviesResponse
import com.emm.data.datasource.local.LocalMovieDataSource
import com.emm.data.datasource.remote.RemoteMovieDataSource
import com.emm.data.localdatabase.entity.MovieEntity
import com.emm.data.mapper.MovieDataMapper
import com.emm.domain.entities.MovieModel
import com.emm.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDataSource: RemoteMovieDataSource,
    private val localMovieDataSource: LocalMovieDataSource,
    private val movieDataMapper: MovieDataMapper
) : MovieRepository {

    override fun loadMoviesList(): Flow<Result<List<MovieModel>>> = flow {
        val movies = localMovieDataSource.getMoviesList()

        if (movies.isEmpty()) {
            when (val fetchMovies: Result<MoviesResponse> = movieDataSource.getMoviesList()) {
                is Result.Error -> emit(Result.Error(fetchMovies.error))
                is Result.Success -> {
                    localMovieDataSource.insertMovies(fetchMovies.data.items.map(movieDataMapper::mapMovieListResponseToEntity))

                    val moviesDomain: Result<List<MovieModel>> = fetchMovies.mapper {
                        it.items.map(movieDataMapper::mapMovieListResponseToDomainModel)
                    }
                    emit(moviesDomain)
                }
            }
        } else {
            emit(Result.Success(movies.map(movieDataMapper::mapMoviesListEntityToDomainModel)))
        }

    }

    override fun getMovieById(movieId: String): Flow<Result<MovieModel>> = flow {
        val movie: MovieEntity? = localMovieDataSource.getMovieById(movieId)

        if (movie != null) {
            emit(Result.Success(movieDataMapper.mapMoviesListEntityToDomainModel(movie)))
        } else {
            emit(Result.Error())
        }
    }

}