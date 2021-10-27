package com.example.cache.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "reviews_response_table", indices = [Index("movieId")])
data class ReviewsCacheResponse(
    @PrimaryKey(autoGenerate = false)
    val movieId: Int,
    val page: Int,
    val results: List<ReviewEntity>,
)
