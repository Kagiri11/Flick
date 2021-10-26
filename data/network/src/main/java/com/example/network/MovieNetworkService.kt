package com.example.network

import com.example.domain.models.reviews.ReviewsResponse
import com.example.domain.utils.Constants.API_KEY
import com.example.network.models.MovieDto
import com.example.network.models.MoviesResponseDto
import com.example.network.models.ReviewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieNetworkService {

    @GET("movie/{movie_id}")
    fun fetchMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): MovieDto

    @GET("movie/upcoming")
    fun fetchUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") pageNumber: Int = 1,
        @Query("language") language: String = "en"
    ): MoviesResponseDto

    @GET("movie/top_rated")
    fun fetchTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") pageNumber: Int = 1,
        @Query("language") language: String = "en"
    ): MoviesResponseDto

    @GET("movie/popular")
    fun fetchPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") pageNumber: Int = 1,
        @Query("language") language: String = "en"
    ): MoviesResponseDto

    @GET("movie/{movie_id}/similar")
    fun fetchSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): MoviesResponseDto

    @GET("movie/{movie_id}/reviews")
    fun fetchMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): ReviewsResponseDto

    @GET("movie/now_playing")
    fun fetchNowPlayingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page")page: Int = 1
    ): MoviesResponseDto
}
