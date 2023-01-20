package com.emm.moviesapp.fragments.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emm.core.Result
import com.emm.domain.entities.MovieModel
import com.emm.domain.usecases.GetMoviesListUseCase
import com.emm.moviesapp.fragments.movieslist.state.MoviesListState
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
class MoviesListViewModel @Inject constructor(
    getMoviesListUseCase: GetMoviesListUseCase,
    private val movieUIMapper: MovieUIMapper
) : ViewModel() {

    private val _viewState = MutableStateFlow(MoviesListState())
    val viewState get() = _viewState.asStateFlow()

    init {
        getMoviesListUseCase()
            .onStart {
                _viewState.update { it.copy(isLoading = true) }
            }
            .onEach(::handleGetMoviesListUseCase)
            .launchIn(viewModelScope)
    }

    private fun handleGetMoviesListUseCase(result: Result<List<MovieModel>>) {
        when (result) {
            is Result.Error -> {
                _viewState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = result.error?.message
                    )
                }
            }
            is Result.Success -> {
                val moviesList = result.data.map(movieUIMapper::mapMovieListDomainToUI)
                _viewState.update {
                    it.copy(isLoading = false, moviesList = moviesList)
                }
            }
        }
    }

}