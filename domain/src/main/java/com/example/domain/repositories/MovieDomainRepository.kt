package com.example.domain.repositories

import com.example.domain.models.MoviesResponse

interface MovieDomainRepository {

    suspend fun fetchUpcomingMovies(): MoviesResponse

    suspend fun fetchTopRatedMovies(): MoviesResponse

    suspend fun fetchPopularMovies(): MoviesResponse

    suspend fun getNowPlaying(): MoviesResponse
}
