package com.example.repository.mappers

import com.example.domain.models.Movie
import com.example.domain.models.MoviesResponse
import com.example.domain.models.reviews.AuthorDetails
import com.example.domain.models.reviews.Review
import com.example.domain.models.reviews.ReviewsResponse
import com.example.network.models.AuthorDetailsDto
import com.example.network.models.MovieDto
import com.example.network.models.MoviesResponseDto
import com.example.network.models.ReviewDto
import com.example.network.models.ReviewsResponseDto

fun AuthorDetailsDto.toDomain(): AuthorDetails {
    return AuthorDetails(
        avatar_path = avatar_path,
        name = name,
        rating = rating,
        username = username
    )
}

fun ReviewDto.toDomain(): Review {
    return Review(
        author = author,
        author_details = author_details.toDomain(),
        content = content,
        created_at = created_at,
        id = id,
        updated_at = updated_at,
        url = url
    )
}

fun ReviewsResponseDto.toDomain(): ReviewsResponse {
    return ReviewsResponse(
        id = id,
        page = page,
        results = results.map { it.toDomain() },
    )
}

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = poster_path,
        releaseDate = release_date,
        overview = overview,
        voteAverage = vote_average

    )
}

fun MoviesResponseDto.toDomain(): MoviesResponse {
    return MoviesResponse(
        page = page,
        results = results.map { it.toDomain() }
    )
}
