package com.example.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.models.ReviewsCacheResponse

@Dao
interface ReviewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieReview(reviewsCacheResponse: ReviewsCacheResponse)

    @Query("SELECT * FROM reviews_response_table WHERE movieId=:movieId")
    fun getMovieReviews(movieId: Int): ReviewsCacheResponse
}
