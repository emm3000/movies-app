package com.emm.moviesapp.fragments.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emm.core.Result
import com.emm.domain.entities.MovieWithSimilarGenresModel
import com.emm.domain.usecases.GetMovieByIdAndSimilarGenresUseCase
import com.emm.moviesapp.fragments.moviedetail.state.MovieDetailState
import com.emm.moviesapp.mapper.MovieUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUIMapper: MovieUIMapper,
    getMovieByIdAndSimilarGenresUseCase: GetMovieByIdAndSimilarGenresUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val movieId: String = savedStateHandle["movieId"] ?: ""

    private val _viewState = MutableStateFlow(MovieDetailState())
    val viewState get() = _viewState.asStateFlow()

    init {
        getMovieByIdAndSimilarGenresUseCase.invoke(movieId)
            .onStart { _viewState.update { it.copy(isLoading = true) } }
            .onEach(::handleProdGetMovieByIdUseCase)
            .launchIn(viewModelScope)
    }

    private fun handleProdGetMovieByIdUseCase(result: Result<MovieWithSimilarGenresModel>) {
        when (result) {
            is Result.Error -> {
                result.failure?.error?.printStackTrace()
                _viewState.update { it.copy(isLoading = false, errorMessage = result.failure?.message) }
            }
            is Result.Success -> {
                _viewState.update {
                    it.copy(
                        currentMovie = movieUIMapper.mapMovieListDomainToUI(result.data.movie),
                        similarGenres = result.data.similarGenres.map(movieUIMapper::mapMovieListDomainToUI).shuffled(),
                        similarDirectors = result.data.similarGenres.map(movieUIMapper::mapMovieListDomainToUI).shuffled(),
                        similarStars = result.data.similarGenres.map(movieUIMapper::mapMovieListDomainToUI).shuffled(),
                        isLoading = false
                    )
                }
            }
        }
    }

}