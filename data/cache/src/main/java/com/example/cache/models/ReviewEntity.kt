package com.example.cache.models

data class ReviewEntity(
    val id: String,
    val author: String,
    val author_details: AuthorEntityDetails,
    val content: String,
    val created_at: String,
    val updated_at: String,
    val url: String
)
