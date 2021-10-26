package com.example.domain.repositories

import com.example.domain.models.Movie
import com.example.domain.models.MoviesResponse
import com.example.domain.models.reviews.ReviewsResponse

interface MovieDomainRepository {

    fun fetchMovieDetails(movieId: Int): Movie

    fun fetchUpcomingMovies(): MoviesResponse

    fun fetchTopRatedMovies(): MoviesResponse

    fun fetchPopularMovies(): MoviesResponse

    fun fetchSimilarMovies(movieId: Int): MoviesResponse

    fun fetchMovieReviews(movieId: Int): ReviewsResponse

    fun getNowPlaying(): MoviesResponse
}
