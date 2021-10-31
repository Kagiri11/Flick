package com.example.domain.usecases

import com.example.domain.models.Movie
import com.example.domain.repositories.MovieDomainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FetchPopularMovies(private val repository: MovieDomainRepository) {
    suspend operator fun invoke(): Flow<List<Movie>> {
        val result = repository.fetchPopularMovies()
        return flowOf(result)
    }
}
