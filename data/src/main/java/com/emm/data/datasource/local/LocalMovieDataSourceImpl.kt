package com.emm.data.datasource.local

import com.emm.data.localdatabase.dao.MovieDao
import com.emm.data.localdatabase.entity.MovieEntity
import com.emm.data.utils.safeDataBaseOperations
import javax.inject.Inject

class LocalMovieDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
) : LocalMovieDataSource {

    override suspend fun getMoviesList(): List<MovieEntity> {
        return safeDataBaseOperations(emptyList()) {
            movieDao.getMovies()
        }
    }

    override suspend fun getMovieById(id: String): MovieEntity? {
        return safeDataBaseOperations(null) {
            movieDao.getMovieById(id)
        }
    }

    override suspend fun insertMovies(movies: List<MovieEntity>) {
        return safeDataBaseOperations(Unit) {
            movieDao.insertMovies(movies)
        }
    }
}
