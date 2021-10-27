package com.example.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val overview: String,
    val voteAverage: Double,
)
