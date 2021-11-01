package com.example.domain.usecases

import com.example.domain.models.Movie
import com.example.domain.repositories.MovieDomainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SearchMovies(private val repository: MovieDomainRepository) {
    suspend operator fun invoke(query: String): Flow<List<Movie>> {
        val searchedMovies = repository.searchMovies(query)
        return flowOf(searchedMovies)
    }
}
