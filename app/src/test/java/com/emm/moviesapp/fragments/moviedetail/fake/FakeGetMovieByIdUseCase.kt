package com.emm.moviesapp.fragments.moviedetail.fake

import com.emm.core.Result
import com.emm.domain.entities.MovieModel
import com.emm.domain.usecases.GetMovieByIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeGetMovieByIdUseCase : GetMovieByIdUseCase {

    private val moviesList = generateFakeData()

    private val flow = MutableSharedFlow<Result<MovieModel>>()

    suspend fun emit(id: String) {
        val movie: MovieModel? = moviesList.firstOrNull { it.id == id }
        if (movie != null) {
            flow.emit(Result.Success(movie))
        } else {
            flow.emit(Result.Error())
        }

    }

    override fun invoke(id: String): Flow<Result<MovieModel>> {
        return flow
    }

    private fun generateFakeData(): List<MovieModel> {
        val list = mutableListOf<MovieModel>()
        repeat(10) {
            list.add(
                MovieModel(
                    contentRating = "",
                    directors = "",
                    fullTitle = "",
                    genres = "",
                    id = it.toString(),
                    image = "",
                    plot = "",
                    releaseState = "",
                    stars = "",
                    title = "",
                    year = ""
                )
            )
        }
        return list
    }

}