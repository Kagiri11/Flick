package com.example.domain.usecases

import com.example.domain.models.CastDetails
import com.example.domain.repositories.MovieDetailsDomainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FetchMovieCast(private val repository: MovieDetailsDomainRepository) {

    suspend operator fun invoke(movieId: Int): Flow<CastDetails> {
        val repositoryResponse = repository.fetchMovieCast(movieId)
        return flowOf(repositoryResponse)
    }
}
