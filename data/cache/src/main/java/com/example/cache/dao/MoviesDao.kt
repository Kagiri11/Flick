package com.example.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.models.popularmovies.PopularMovieEntity
import com.example.cache.models.topRatedMovies.NowPlayingMovieEntity
import com.example.cache.models.upcomingmovies.UpcomingMovieEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM upcoming_movies_table")
    suspend fun getUpcomingMovies(): List<UpcomingMovieEntity>

    @Query("SELECT * FROM now_playing_movies_table")
    suspend fun getNowPlayingMovies(): List<NowPlayingMovieEntity>

    @Query("SELECT * FROM popular_movies_table")
    suspend fun getPopularMovies(): List<UpcomingMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUpcomingMovies(movies: UpcomingMovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNowPlayingMovie(movies: NowPlayingMovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPopularMovies(movies: PopularMovieEntity)
}
