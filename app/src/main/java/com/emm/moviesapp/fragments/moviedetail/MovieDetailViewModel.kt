package com.emm.moviesapp.fragments.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emm.core.Result
import com.emm.domain.entities.MovieModel
import com.emm.domain.usecases.GetMovieByIdUseCase
import com.emm.moviesapp.mapper.MovieUIMapper
import com.emm.moviesapp.model.MovieUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    getMovieByIdUseCase: GetMovieByIdUseCase,
    savedStateHandle: SavedStateHandle,
    private val movieUIMapper: MovieUIMapper
) : ViewModel() {

    private val movieId = savedStateHandle["movieId"] ?: ""

    private val _movieDetail = MutableStateFlow<MovieUI?>(null)
    val movieDetail get() = _movieDetail.asStateFlow()

    init {
        getMovieByIdUseCase.invoke(movieId)
            .onEach(::handleProdGetMovieByIdUseCase)
            .launchIn(viewModelScope)
    }

    private fun handleProdGetMovieByIdUseCase(result: Result<MovieModel>) {
        when (result) {
            is Result.Error -> {}
            is Result.Success -> {
                _movieDetail.value = movieUIMapper.mapMovieListDomainToUI(result.data)
            }
        }
    }

}