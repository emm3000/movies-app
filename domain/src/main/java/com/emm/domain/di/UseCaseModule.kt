package com.emm.domain.di

import com.emm.domain.usecases.GetMovieByIdUseCase
import com.emm.domain.usecases.GetMoviesListUseCase
import com.emm.domain.usecases.ProdGetMovieByIdUseCase
import com.emm.domain.usecases.ProdGetMoviesListUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetMovieListUseCase(
        prodGetMoviesListUseCase: ProdGetMoviesListUseCase
    ): GetMoviesListUseCase

    @Binds
    abstract fun bindGetMovieByIdUseCase(
        prodGetMovieByIdUseCase: ProdGetMovieByIdUseCase
    ): GetMovieByIdUseCase

}