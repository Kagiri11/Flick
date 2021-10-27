package com.example.domain.usecases

import com.example.domain.models.Movie
import com.example.domain.repositories.MovieDomainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FetchSimilarMovies(private val repository: MovieDomainRepository) {

    suspend operator fun invoke(movieId: Int): Flow<List<Movie>> {
        return flowOf(repository.fetchSimilarMovies(movieId).results)
    }
}
