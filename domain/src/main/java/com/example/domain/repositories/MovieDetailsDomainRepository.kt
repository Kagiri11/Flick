package com.example.domain.repositories

import com.example.domain.models.CastDetails
import com.example.domain.models.Movie
import com.example.domain.models.MoviesResponse
import com.example.domain.models.reviews.ReviewsResponse

interface MovieDetailsDomainRepository {

    suspend fun fetchMovieDetails(movieId: Int): Movie

    suspend fun fetchSimilarMovies(movieId: Int): MoviesResponse

    suspend fun fetchMovieReviews(movieId: Int): ReviewsResponse

    suspend fun fetchMovieCast(movieId: Int): CastDetails
}
