package com.example.repository.sources

import com.example.cache.db.AppDataBase
import com.example.domain.models.CastDetails
import com.example.domain.models.Movie
import com.example.domain.models.MoviesResponse
import com.example.domain.models.reviews.ReviewsResponse
import com.example.domain.repositories.MovieDetailsDomainRepository
import com.example.remote.MovieNetworkService
import com.example.repository.mappers.toDomain

class MovieDetailsDataRepository(
    private val network: MovieNetworkService,
    private val appDataBase: AppDataBase
) : MovieDetailsDomainRepository {
    override suspend fun fetchMovieDetails(movieId: Int): Movie {
        val networkMovieDetails = network.fetchMovieDetails(movieId)
        return networkMovieDetails.toDomain()
    }

    override suspend fun fetchSimilarMovies(movieId: Int): MoviesResponse {
        val networkSimilarMovies = network.fetchSimilarMovies(movieId)
        return networkSimilarMovies.toDomain()
    }

    override suspend fun fetchMovieReviews(movieId: Int): ReviewsResponse {
        val networkMovieReviews = network.fetchMovieReviews(movieId)
        return networkMovieReviews.toDomain()
    }

    override suspend fun fetchMovieCast(movieId: Int): CastDetails {
        val movieCast = network.fetchMovieCast(movieId)
        return movieCast.toDomain()
    }
}
