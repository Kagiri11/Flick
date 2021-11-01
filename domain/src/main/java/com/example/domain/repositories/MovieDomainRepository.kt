package com.example.domain.repositories

import com.example.domain.models.Movie

interface MovieDomainRepository {

    suspend fun fetchUpcomingMovies(pageNumber: Int): List<Movie>

    suspend fun fetchPopularMovies(): List<Movie>

    suspend fun getNowPlaying(): List<Movie>

    suspend fun searchMovies(query: String): List<Movie>
}
