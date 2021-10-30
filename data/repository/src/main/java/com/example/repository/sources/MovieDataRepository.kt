package com.example.repository.sources

import com.example.cache.db.AppDataBase
import com.example.domain.models.MoviesResponse
import com.example.domain.repositories.MovieDomainRepository
import com.example.remote.MovieNetworkService
import com.example.repository.mappers.toDomain

class MovieDataRepository(
    private val network: MovieNetworkService,
    private val appDataBase: AppDataBase
) : MovieDomainRepository {

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

    override suspend fun getNowPlaying(): MoviesResponse {
        val networkNowPlaying = network.fetchNowPlayingMovies()
        return networkNowPlaying.toDomain()
    }
}
