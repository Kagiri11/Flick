package com.example.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cache.dao.MoviesDao
import com.example.cache.dao.ReviewsDao
import com.example.cache.models.ReviewEntity
import com.example.cache.models.ReviewsCacheResponse

@Database(entities = [ReviewEntity::class, ReviewsCacheResponse::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun reviewsDao(): ReviewsDao
}
