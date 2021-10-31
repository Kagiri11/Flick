package com.example.cache.models.upcomingmovies

data class UpcomingMovieResponseEntity(
    val page: Int,
    val results: List<UpcomingMovieEntity>,
)
