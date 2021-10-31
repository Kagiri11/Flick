package com.example.repository.sources

import com.example.cache.dao.MoviesDao
import com.example.domain.models.Movie
import com.example.domain.repositories.MovieDomainRepository
import com.example.remote.MovieNetworkService
import com.example.repository.mappers.toDomain
import com.example.repository.mappers.toEntity
import com.example.repository.mappers.toPopularEntity
import com.example.repository.mappers.toTopRatedEntity

class MovieDataRepository(
    private val network: MovieNetworkService,
    private val moviesDao: MoviesDao
) : MovieDomainRepository {

    override suspend fun fetchUpcomingMovies(): List<Movie> {
        val cachedMovies = moviesDao.getUpcomingMovies()
        return if (cachedMovies.isNotEmpty()) {
            cachedMovies.map { it.toDomain() }
        } else {
            val networkUpcomingMovies = network.fetchUpcomingMovies()
            networkUpcomingMovies.results.forEach {
                moviesDao.addUpcomingMovies(it.toDomain().toEntity())
            }
            networkUpcomingMovies.results.map { it.toDomain() }
        }
    }

    override suspend fun getNowPlaying(): List<Movie> {
        val cachedMovies = moviesDao.getNowPlayingMovies()
        val networkNowPlayingMovies = network.fetchNowPlayingMovies()
        return if (cachedMovies.isNotEmpty()) {
            cachedMovies.map { it.toDomain() }
        } else {
            networkNowPlayingMovies.results.forEach {
                moviesDao.addNowPlayingMovie(it.toDomain().toTopRatedEntity())
            }
            networkNowPlayingMovies.results.map { it.toDomain() }
        }
    }

    override suspend fun fetchPopularMovies(): List<Movie> {
        val networkPopularMovies = network.fetchPopularMovies()
        val cachedMovies = moviesDao.getPopularMovies()
        return if (cachedMovies.isNotEmpty()) {
            cachedMovies.map { it.toDomain() }
        } else {
            networkPopularMovies.results.forEach {
                moviesDao.addPopularMovies(it.toDomain().toPopularEntity())
            }
            networkPopularMovies.toDomain().results
        }
    }
}
