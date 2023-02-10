package com.emm.data.di

import com.emm.data.datasource.local.LocalMovieDataSource
import com.emm.data.datasource.local.LocalMovieDataSourceImpl
import com.emm.data.datasource.remote.RemoteMovieDataSource
import com.emm.data.datasource.remote.RemoteMovieDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteMovieDataSource(remoteMovieDataSourceImpl: RemoteMovieDataSourceImpl): RemoteMovieDataSource

    @Binds
    abstract fun bindLocalMovieDataSource(localMovieDataSourceImpl: LocalMovieDataSourceImpl): LocalMovieDataSource
}
