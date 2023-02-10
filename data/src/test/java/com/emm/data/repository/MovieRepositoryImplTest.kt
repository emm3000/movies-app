package com.emm.data.repository

import com.emm.core.Result
import com.emm.data.api.response.MovieResponse
import com.emm.data.api.response.MoviesResponse
import com.emm.data.datasource.local.LocalMovieDataSource
import com.emm.data.datasource.remote.RemoteMovieDataSource
import com.emm.data.localdatabase.entity.MovieEntity
import com.emm.data.mapper.MovieDataMapperImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieRepositoryImplTest {

    private lateinit var movieRepositoryImpl: MovieRepositoryImpl

    @RelaxedMockK
    lateinit var remoteMovieDataSource: RemoteMovieDataSource

    @RelaxedMockK
    lateinit var localMovieDataSource: LocalMovieDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        movieRepositoryImpl = MovieRepositoryImpl(
            movieDataSource = remoteMovieDataSource,
            localMovieDataSource = localMovieDataSource,
            movieDataMapper = MovieDataMapperImpl(),
        )
    }

    @Test
    fun `Verify that the api call is not made if movies exist in the database`() = runTest {
        coEvery { remoteMovieDataSource.getMoviesList() } returns Result.Success(fakeMovieResponse())
        coEvery { localMovieDataSource.getMoviesList() } returns fakeMovieEntityList()

        movieRepositoryImpl.loadMoviesList().first()

        coVerify(exactly = 0) { remoteMovieDataSource.getMoviesList() }
        coVerify(exactly = 0) { localMovieDataSource.insertMovies(any()) }
    }

    @Test
    fun `Verify insertion and call to movie api when database is empty`() = runTest {
        coEvery { remoteMovieDataSource.getMoviesList() } returns Result.Success(fakeMovieResponse())
        coEvery { localMovieDataSource.getMoviesList() } returns emptyList()

        movieRepositoryImpl.loadMoviesList().first()

        coVerify(exactly = 1) { remoteMovieDataSource.getMoviesList() }
        coVerify(exactly = 1) { localMovieDataSource.insertMovies(any()) }
    }

    @Test
    fun `Validate that the list of movies in the database is queried when a movie exists by its id`() = runTest {
        val movieID = "3"
        coEvery { localMovieDataSource.getMoviesList() } returns fakeMovieEntityList()
        coEvery { localMovieDataSource.getMovieById(any()) } returns fakeMovieEntity()

        movieRepositoryImpl.getMovieByIdWithSimilarGenres(movieID).first()

        coVerify(exactly = 1) { localMovieDataSource.getMoviesList() }
    }

    @Test
    fun `Validate that similar movies exist`() = runTest {
        val movieID = "3"
        coEvery { localMovieDataSource.getMoviesList() } returns fakeMovieEntityList()
        coEvery { localMovieDataSource.getMovieById(any()) } returns fakeMovieEntity()

        val movieWithSimilarGenresModelResult = movieRepositoryImpl.getMovieByIdWithSimilarGenres(movieID).first()

        assertThat((movieWithSimilarGenresModelResult as Result.Success).data.similarGenres).isNotEmpty()
    }

    companion object {
        fun fakeMovieResponse(): MoviesResponse {
            return MoviesResponse(
                errorMessage = "",
                items = fakeMovieResponseList(),
            )
        }

        private fun fakeMovieResponseList(): List<MovieResponse> {
            return MutableList(10) {
                MovieResponse(
                    id = it.toString(),
                    contentRating = "",
                    directors = "",
                    fullTitle = "",
                    genres = "",
                    image = "",
                    plot = "",
                    releaseState = "",
                    stars = "",
                    title = "",
                    year = "",
                )
            }
        }

        fun fakeMovieEntityList(): List<MovieEntity> {
            return MutableList(10) {
                MovieEntity(
                    id = it.toString(),
                    contentRating = "",
                    directors = "",
                    fullTitle = "",
                    genres = "Action, Drama",
                    image = "",
                    plot = "",
                    releaseState = "",
                    stars = "",
                    title = "",
                    year = "",
                )
            }
        }

        fun fakeMovieEntity(): MovieEntity {
            return MovieEntity(
                id = "3",
                contentRating = "",
                directors = "",
                fullTitle = "",
                genres = "Action",
                image = "",
                plot = "",
                releaseState = "",
                stars = "",
                title = "",
                year = "",
            )
        }
    }
}
