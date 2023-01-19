package com.emm.data.di

import com.emm.data.mapper.MovieDataMapper
import com.emm.data.mapper.MovieDataMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MappersModule {

    @Binds
    abstract fun bindMovieDataMapper(movieDataMapperImpl: MovieDataMapperImpl): MovieDataMapper

}