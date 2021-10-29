package com.example.remote

import com.example.domain.utils.Constants.API_KEY
import com.example.remote.models.MovieDto
import com.example.remote.models.MoviesResponseDto
import com.example.remote.models.ReviewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieNetworkService {

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): MovieDto

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") pageNumber: Int = 1,
        @Query("language") language: String = "en"
    ): MoviesResponseDto

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") pageNumber: Int = 1,
        @Query("language") language: String = "en"
    ): MoviesResponseDto

    @GET("movie/popular")
    suspend fun fetchPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") pageNumber: Int = 1,
        @Query("language") language: String = "en"
    ): MoviesResponseDto

    @GET("movie/{movie_id}/similar")
    suspend fun fetchSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): MoviesResponseDto

    @GET("movie/{movie_id}/reviews")
    suspend fun fetchMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): ReviewsResponseDto

    @GET("movie/now_playing")
    suspend fun fetchNowPlayingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page")page: Int = 1
    ): MoviesResponseDto
}
