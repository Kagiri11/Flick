package com.example.domain.repositories

import com.example.domain.models.Movie
import com.example.domain.models.MoviesResponse
import com.example.domain.models.reviews.ReviewsResponse

interface MovieDomainRepository {

    suspend fun fetchMovieDetails(movieId: Int): Movie

    suspend fun fetchUpcomingMovies(): MoviesResponse

    suspend fun fetchTopRatedMovies(): MoviesResponse

    suspend fun fetchPopularMovies(): MoviesResponse

    suspend fun fetchSimilarMovies(movieId: Int): MoviesResponse

    suspend fun fetchMovieReviews(movieId: Int): ReviewsResponse

    suspend fun getNowPlaying(): MoviesResponse
}
