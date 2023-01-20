package com.emm.data.localdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

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