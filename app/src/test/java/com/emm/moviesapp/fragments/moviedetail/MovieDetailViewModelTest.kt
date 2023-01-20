package com.emm.moviesapp.fragments.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.emm.moviesapp.fragments.moviedetail.fake.FakeGetMovieByIdUseCase
import com.emm.moviesapp.mapper.MovieUIMapperImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailViewModelTest {
    private val movieUIMapper = MovieUIMapperImpl()

    @RelaxedMockK
    lateinit var savedStateHandle: SavedStateHandle

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun `Verify that a movie has been obtained by its id`() = runTest {
        val movieID = "2"
        coEvery { savedStateHandle.get<String>(any()) } returns movieID

        val fakeRepo = FakeGetMovieByIdUseCase()

        val viewModel = MovieDetailViewModel(
            getMovieByIdUseCase = fakeRepo,
            movieUIMapper = movieUIMapper,
            savedStateHandle = savedStateHandle
        )

        delay(1L)

        fakeRepo.emit(movieID)

        assertThat(viewModel.movieDetail.value?.id).isEqualTo(movieID)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}