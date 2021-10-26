package com.example.network.models

data class ReviewsResponseDto(
    val id: Int,
    val page: Int,
    val results: List<ReviewDto>,
    val total_pages: Int,
    val total_results: Int
)
