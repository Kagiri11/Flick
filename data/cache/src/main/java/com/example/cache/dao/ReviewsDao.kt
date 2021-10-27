package com.example.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.models.MovieEntity
import com.example.cache.models.ReviewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieReview(movieEntity: MovieEntity)

    @Query("SELECT * FROM reviews_table")
    fun getMovieReviews(): Flow<List<ReviewEntity>>
}
