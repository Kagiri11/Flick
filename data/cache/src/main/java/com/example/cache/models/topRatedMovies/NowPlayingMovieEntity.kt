package com.example.cache.models.topRatedMovies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "now_playing_movies_table")
data class NowPlayingMovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val overview: String,
    val voteAverage: Double,
)
