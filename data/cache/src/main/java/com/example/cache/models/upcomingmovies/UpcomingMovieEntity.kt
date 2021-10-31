package com.example.cache.models.upcomingmovies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "upcoming_movies_table")
data class UpcomingMovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val overview: String,
    val voteAverage: Double,
)
