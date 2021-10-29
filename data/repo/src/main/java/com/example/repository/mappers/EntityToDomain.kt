package com.example.repository.mappers

import com.example.cache.models.AuthorEntityDetails
import com.example.cache.models.MovieEntity
import com.example.cache.models.ReviewEntity
import com.example.cache.models.ReviewsCacheResponse
import com.example.domain.models.Movie
import com.example.domain.models.reviews.AuthorDetails
import com.example.domain.models.reviews.Review
import com.example.domain.models.reviews.ReviewsResponse

fun AuthorEntityDetails.toDomain(): AuthorDetails {
    return AuthorDetails(
        avatar_path = avatar_path,
        name = name,
        rating = rating,
        username = username
    )
}

fun ReviewEntity.toDomain(): Review {
    return Review(
        author_details = author_details.toDomain(),
        author = author, content = content,
        created_at = created_at,
        id = id,
        updated_at = updated_at,
        url = url
    )
}

fun ReviewsCacheResponse.toDomain(): ReviewsResponse {
    return ReviewsResponse(
        id = movieId,
        page = page,
        results = results.map { it.toDomain() }
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath, releaseDate, overview, voteAverage
    )
}
