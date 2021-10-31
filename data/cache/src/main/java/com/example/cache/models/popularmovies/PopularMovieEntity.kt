package com.example.cache.models.popularmovies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_movies_table")
data class PopularMovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val overview: String,
    val voteAverage: Double,
)
