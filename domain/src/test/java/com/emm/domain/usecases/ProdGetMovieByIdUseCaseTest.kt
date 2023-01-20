package com.emm.domain.usecases

import com.emm.core.Result
import com.emm.core.Result.*
import com.emm.domain.entities.MovieModel
import com.emm.domain.usecases.fake.FakeMovieRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProdGetMovieByIdUseCaseTest {

    private val fakeMovieRepository = FakeMovieRepository()

    private val useCase = ProdGetMovieByIdUseCase(
        movieRepository = fakeMovieRepository
    )

    @Before
    fun loadFakeData() {
        fakeMovieRepository.loadMovies(generateFakeData())
    }

    @Test
    fun `check the search for a movie by its id`() = runTest {
        val expectedResult: Success<MovieModel> = Success(generateFakeData()[2])
        val movieTwo: Result<MovieModel> = useCase.invoke(id = "2").first()

        assertThat(movieTwo).isEqualTo(expectedResult)

    }

    @Test
    fun `Verify an erroneous result when no movie exists in the database`() = runTest {
        val movieNothing: Result<MovieModel> = useCase.invoke(id = "20").first()

        assertThat(movieNothing).isInstanceOf(Error::class.java)
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