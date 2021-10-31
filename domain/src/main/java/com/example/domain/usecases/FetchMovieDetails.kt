package com.example.domain.usecases

import com.example.domain.models.Movie
import com.example.domain.repositories.MovieDetailsDomainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FetchMovieDetails(private val repository: MovieDetailsDomainRepository) {

    suspend operator fun invoke(movieId: Int): Flow<Movie> {
        val repositoryResponse = repository.fetchMovieDetails(movieId)
        return flowOf(repositoryResponse)
    }
}
