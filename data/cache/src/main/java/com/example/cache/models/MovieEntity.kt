package com.example.cache.models

data class MovieEntity(
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val overview: String,
    val voteAverage: Double,
)
