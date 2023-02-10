package com.emm.domain.usecases.fake

import com.emm.core.Result
import com.emm.domain.entities.MovieModel
import com.emm.domain.entities.MovieWithSimilarGenresModel
import com.emm.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

typealias SuccessResultList = Result<List<MovieModel>>

class FakeMovieRepository : MovieRepository {

    private val movieList: MutableList<MovieModel> = mutableListOf()

    fun loadMovies(movieList: List<MovieModel>) {
        this.movieList.addAll(movieList)
    }

    override fun loadMoviesList(): Flow<SuccessResultList> {
        return flowOf(Result.Success(movieList))
    }

    override fun getMovieByIdWithSimilarGenres(movieId: String): Flow<Result<MovieWithSimilarGenresModel>> {
        return flowOf(
            movieList.firstOrNull { it.id == movieId }?.let {
                Result.Success(
                    MovieWithSimilarGenresModel(
                        movie = it,
                        similarGenres = emptyList(),
                    ),
                )
            } ?: run {
                Result.Error()
            },
        )
    }
}
