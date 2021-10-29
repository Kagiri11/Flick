package com.example.repository.sources

import com.example.cache.db.AppDataBase
import com.example.domain.models.Movie
import com.example.domain.models.MoviesResponse
import com.example.domain.models.reviews.ReviewsResponse
import com.example.domain.repositories.MovieDomainRepository
import com.example.repository.mappers.toDomain

class MovieDataRepository(
    private val network: com.example.network.MovieNetworkService,
    private val appDataBase: AppDataBase
) : MovieDomainRepository {

    override suspend fun fetchMovieDetails(movieId: Int): Movie {
        val networkMovieDetails = network.fetchMovieDetails(movieId)
        return networkMovieDetails.toDomain()
    }

    override suspend fun fetchUpcomingMovies(): MoviesResponse {
        val networkUpcomingMovies = network.fetchUpcomingMovies()
        return networkUpcomingMovies.toDomain()
    }

    override suspend fun fetchTopRatedMovies(): MoviesResponse {
        val networkTopRatedMovies = network.fetchTopRatedMovies()
        return networkTopRatedMovies.toDomain()
    }

    override suspend fun fetchPopularMovies(): MoviesResponse {
        val networkPopularMovies = network.fetchPopularMovies()
        return networkPopularMovies.toDomain()
    }

    override suspend fun fetchSimilarMovies(movieId: Int): MoviesResponse {
        val networkSimilarMovies = network.fetchSimilarMovies(movieId)
        return networkSimilarMovies.toDomain()
    }

    override suspend fun fetchMovieReviews(movieId: Int): ReviewsResponse {
        val networkMovieReviews = network.fetchMovieReviews(movieId)
        return networkMovieReviews.toDomain()
    }

    override suspend fun getNowPlaying(): MoviesResponse {
        val networkNowPlaying = network.fetchNowPlayingMovies()
        return networkNowPlaying.toDomain()
    }
}
