package com.example.cache.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "reviews_table",
    foreignKeys = [
        ForeignKey(entity = ReviewsCacheResponse::class, parentColumns = ["movieId"], childColumns = ["movieId"], onDelete = CASCADE)
    ],
    indices = [Index("movieId")]
)
data class ReviewEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val movieId: Int?,
    val author: String,
    val author_details: AuthorEntityDetails,
    val content: String,
    val created_at: String,
    val updated_at: String,
    val url: String
)
