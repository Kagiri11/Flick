package com.example.network.models

data class MoviesResponseDto(
    val dates: DatesDto,
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)
