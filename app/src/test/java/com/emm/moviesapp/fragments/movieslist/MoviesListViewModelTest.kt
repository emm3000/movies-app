package com.emm.moviesapp.fragments.movieslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.emm.core.Result
import com.emm.domain.entities.MovieModel
import com.emm.domain.usecases.GetMoviesListUseCase
import com.emm.moviesapp.mapper.MovieUIMapperImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesListViewModelTest {
    private val movieUIMapper = MovieUIMapperImpl()

    @RelaxedMockK
    lateinit var getMoviesListUseCase: GetMoviesListUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun `Validate the use case movie list`() = runTest {
        coEvery { getMoviesListUseCase.invoke() } returns flowOf(Result.Success(generateFakeData()))

        val viewModel = MoviesListViewModel(
            getMoviesListUseCase = getMoviesListUseCase,
            movieUIMapper = movieUIMapper
        )

        assertThat(viewModel.viewState.value.isLoading).isFalse()

        delay(1L)

        assertThat(viewModel.viewState.value.moviesList.size).isEqualTo(generateFakeData().size)

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

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}