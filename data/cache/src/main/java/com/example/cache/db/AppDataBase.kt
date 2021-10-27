package com.example.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cache.converters.AuthorDetailsConverter
import com.example.cache.converters.ReviewsConverter
import com.example.cache.dao.MoviesDao
import com.example.cache.dao.ReviewsDao
import com.example.cache.models.MovieEntity
import com.example.cache.models.ReviewsCacheResponse

@Database(
    entities = [ReviewsCacheResponse::class, MovieEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(AuthorDetailsConverter::class, ReviewsConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun reviewsDao(): ReviewsDao
}
