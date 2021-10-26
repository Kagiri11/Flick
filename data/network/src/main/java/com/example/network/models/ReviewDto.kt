package com.example.network.models

data class ReviewDto(
    val author: String,
    val author_details: AuthorDetailsDto,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)
