package com.emm.data.localdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.emm.data.localdatabase.entity.SearchBy.*

@Entity
data class MovieEntity(
    @PrimaryKey val id: String,
    val contentRating: String,
    val directors: String,
    val fullTitle: String,
    val genres: String,
    val image: String,
    val plot: String,
    val releaseState: String,
    val stars: String,
    val title: String,
    val year: String
)

enum class SearchBy {
    DIRECTORS,
    STARS,
    GENRES
}

fun MovieEntity.propertyFilter(searchBy: SearchBy): String {
    return when (searchBy) {
        DIRECTORS -> directors
        STARS -> stars
        GENRES -> genres
    }
}