package com.emm.moviesapp.fragments.moviedetail.fake

import com.emm.core.Failure
import com.emm.core.Result
import com.emm.domain.entities.MovieModel
import com.emm.domain.entities.MovieWithSimilarGenresModel
import com.emm.domain.usecases.GetMovieByIdAndSimilarGenresUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeGetMovieByIdAndSimilarGenresUseCase : GetMovieByIdAndSimilarGenresUseCase {

    private val moviesList = generateFakeData()

    private val flow = MutableSharedFlow<Result<MovieWithSimilarGenresModel>>()

    suspend fun emit(id: String) {
        val searchedMovie: MovieModel? = moviesList.firstOrNull { it.id == id }
        if (searchedMovie != null) {
            flow.emit(
                Result.Success(
                    MovieWithSimilarGenresModel(
                        movie = searchedMovie,
                        similarGenres = moviesList
                    )
                )
            )
        } else {
            flow.emit(Result.Error(Failure.None))
        }

    }

    override fun invoke(movieID: String): Flow<Result<MovieWithSimilarGenresModel>> {
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