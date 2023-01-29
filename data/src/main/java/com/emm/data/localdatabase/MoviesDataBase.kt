package com.emm.data.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emm.data.localdatabase.dao.MovieDao
import com.emm.data.localdatabase.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class MoviesDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}
