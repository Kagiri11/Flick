package com.example.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cache.converters.AuthorDetailsConverter
import com.example.cache.converters.ReviewsConverter
import com.example.cache.dao.MoviesDao
import com.example.cache.dao.ReviewsDao
import com.example.cache.models.ReviewsCacheResponse
import com.example.cache.models.popularmovies.PopularMovieEntity
import com.example.cache.models.topRatedMovies.NowPlayingMovieEntity
import com.example.cache.models.upcomingmovies.UpcomingMovieEntity

@Database(
    entities = [ReviewsCacheResponse::class, PopularMovieEntity::class, UpcomingMovieEntity::class, NowPlayingMovieEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(AuthorDetailsConverter::class, ReviewsConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun reviewsDao(): ReviewsDao
}
