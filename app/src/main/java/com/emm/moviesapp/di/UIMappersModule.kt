package com.emm.moviesapp.di

import com.emm.moviesapp.mapper.MovieUIMapper
import com.emm.moviesapp.mapper.MovieUIMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UIMappersModule {

    @Binds
    abstract fun bindMovieUIMapper(
        movieUIMapperImpl: MovieUIMapperImpl,
    ): MovieUIMapper
}
