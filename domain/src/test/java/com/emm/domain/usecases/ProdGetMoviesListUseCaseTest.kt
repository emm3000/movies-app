package com.emm.domain.usecases

import com.emm.core.Result
import com.emm.domain.entities.MovieModel
import com.emm.domain.usecases.fake.FakeMovieRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProdGetMoviesListUseCaseTest {
    private val fakeMovieRepository = FakeMovieRepository()

    private val useCase = ProdGetMoviesListUseCase(
        movieRepository = fakeMovieRepository
    )

    @Before
    fun loadFakeData() {
        fakeMovieRepository.loadMovies(generateFakeData())
    }

    @Test
    fun `Verify that the list of movies has been obtained`() = runTest {
        val expectedResult: Result.Success<List<MovieModel>> = Result.Success(generateFakeData())
        val movieList: Result<List<MovieModel>> = useCase.invoke().first()

        Truth.assertThat((movieList as Result.Success).data.size).isEqualTo(expectedResult.data.size)

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