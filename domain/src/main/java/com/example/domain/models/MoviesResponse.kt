package com.example.domain.models

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
)
