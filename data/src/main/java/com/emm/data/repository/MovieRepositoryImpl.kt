package com.emm.data.repository

import com.emm.core.Failure
import com.emm.core.Result
import com.emm.core.mapper
import com.emm.data.datasource.local.LocalMovieDataSource
import com.emm.data.datasource.remote.RemoteMovieDataSource
import com.emm.data.localdatabase.entity.MovieEntity
import com.emm.data.mapper.MovieDataMapper
import com.emm.data.utils.releaseStateToEpochMillis
import com.emm.domain.entities.MovieModel
import com.emm.domain.entities.MovieWithSimilarGenresModel
import com.emm.domain.repository.MovieRepository
import kotlinx.coroutines.delay
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
            when (val fetchMovies = movieDataSource.getMoviesList()) {
                is Result.Error -> emit(Result.Error(fetchMovies.failure))
                is Result.Success -> {

                    val sortedMoviesByReleaseState = fetchMovies.data.items
                        .sortedByDescending { it.releaseState.releaseStateToEpochMillis() }

                    localMovieDataSource.insertMovies(
                        movies = sortedMoviesByReleaseState.map(movieDataMapper::mapMovieListResponseToEntity)
                    )

                    val resultToDomain: Result<List<MovieModel>> = fetchMovies.mapper {
                        sortedMoviesByReleaseState.map(movieDataMapper::mapMovieListResponseToDomainModel)
                    }
                    emit(resultToDomain)
                }
            }
        } else {
            emit(Result.Success(movies.map(movieDataMapper::mapMoviesListEntityToDomainModel)))
        }

    }

    override fun getMovieByIdWithSimilarGenres(movieId: String): Flow<Result<MovieWithSimilarGenresModel>> = flow {
        delay(400L) // To test Loading in [MovieDetailScreen.kt]

        val searchedMovie: MovieEntity? = localMovieDataSource.getMovieById(movieId)
        if (searchedMovie != null) {
            val allMovies: List<MovieEntity> = localMovieDataSource.getMoviesList()
            val similarGenresMovies: List<MovieEntity> = getSimilarGenres(searchedMovie.genres, allMovies, movieId)

            emit(
                Result.Success(
                    MovieWithSimilarGenresModel(
                        movie = movieDataMapper.mapMoviesListEntityToDomainModel(searchedMovie),
                        similarGenres = similarGenresMovies.map(movieDataMapper::mapMoviesListEntityToDomainModel),
                    )
                )
            )
        } else {
            emit(Result.Error(Failure.LocalDataBaseError("No entity")))
        }
    }

    private fun getSimilarGenres(
        genresFromSearchedMovie: String,
        allMovies: List<MovieEntity>,
        movieId: String,
    ): List<MovieEntity> {
        val genresList: List<String> = genresFromSearchedMovie.split(",").map { it.trim() }

        val moviesWithSimilarGenres: List<MovieEntity> = allMovies.filter { movieEntity ->
            val localGenres = movieEntity.genres.split(",").map { it.trim() }
            return@filter genresList.intersect(localGenres.toSet()).isNotEmpty() && movieEntity.id != movieId
        }

        return moviesWithSimilarGenres
    }

}