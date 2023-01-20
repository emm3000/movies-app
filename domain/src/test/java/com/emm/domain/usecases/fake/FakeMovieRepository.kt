package com.emm.domain.usecases.fake

import com.emm.core.Result
import com.emm.core.Result.*
import com.emm.domain.entities.MovieModel
import com.emm.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

typealias SuccessResultList = Result<List<MovieModel>>
typealias SuccessResult = Result<MovieModel>

class FakeMovieRepository : MovieRepository {

    private val movieList: MutableList<MovieModel> = mutableListOf()

    fun loadMovies(movieList: List<MovieModel>) {
        this.movieList.addAll(movieList)
    }

    override fun loadMoviesList(): Flow<SuccessResultList> {
        return flowOf(Success(movieList))
    }

    override fun getMovieById(movieId: String): Flow<SuccessResult> {
        return flowOf(movieList.firstOrNull() { it.id == movieId }?.let {
            Success(it)
        } ?: run {
            Error()
        })
    }

}