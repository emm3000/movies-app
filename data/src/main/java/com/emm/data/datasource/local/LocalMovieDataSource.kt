package com.emm.data.datasource.local

import com.emm.data.localdatabase.entity.MovieEntity

interface LocalMovieDataSource {

    suspend fun getMoviesList(): List<MovieEntity>

    suspend fun getMovieById(id: String): MovieEntity?

    suspend fun insertMovies(movies: List<MovieEntity>)

}