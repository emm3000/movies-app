package com.emm.data.localdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emm.data.localdatabase.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity WHERE id = :movieId")
    suspend fun getMovieById(movieId: String): MovieEntity?

    @Query("SELECT * FROM MovieEntity")
    suspend fun getMovies(): List<MovieEntity>
}
