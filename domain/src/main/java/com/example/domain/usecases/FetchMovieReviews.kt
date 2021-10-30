package com.example.domain.usecases

import com.example.domain.models.reviews.Review
import com.example.domain.repositories.MovieDetailsDomainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FetchMovieReviews(private val repository: MovieDetailsDomainRepository) {
    suspend operator fun invoke(movieId: Int): Flow<List<Review>> {
        return flowOf(repository.fetchMovieReviews(movieId).results)
    }
}
