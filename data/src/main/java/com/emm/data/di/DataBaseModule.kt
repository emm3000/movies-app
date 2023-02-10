package com.emm.data.di

import android.app.Application
import androidx.room.Room
import com.emm.data.localdatabase.MoviesDataBase
import com.emm.data.localdatabase.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDataBase(
        application: Application,
    ): MoviesDataBase {
        return Room.databaseBuilder(
            application,
            MoviesDataBase::class.java,
            "Movie.db",
        ).build()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(moviesDatabase: MoviesDataBase): MovieDao {
        return moviesDatabase.movieDao()
    }
}
