package com.example.domain.models.reviews

data class ReviewsResponse(
    val id: Int,
    val page: Int,
    val results: List<Review>,
)
