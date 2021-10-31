package com.example.repository.mappers

import com.example.domain.models.Cast
import com.example.domain.models.CastDetails
import com.example.domain.models.Crew
import com.example.domain.models.Movie
import com.example.domain.models.MoviesResponse
import com.example.domain.models.reviews.AuthorDetails
import com.example.domain.models.reviews.Review
import com.example.domain.models.reviews.ReviewsResponse
import com.example.remote.models.AuthorDetailsDto
import com.example.remote.models.CastDetailsDto
import com.example.remote.models.CastDto
import com.example.remote.models.CrewDto
import com.example.remote.models.MovieDto
import com.example.remote.models.MoviesResponseDto
import com.example.remote.models.ReviewDto
import com.example.remote.models.ReviewsResponseDto

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

fun CrewDto.toDomain(): Crew {
    return Crew(
        id, job, known_for_department, name, original_name
    )
}

fun CastDto.toDomain(): Cast {
    return Cast(
        id = id,
        cast_id = cast_id,
        character = character,
        name = name,
        original_name = original_name,
        profile_path = profile_path
    )
}

fun CastDetailsDto.toDomain(): CastDetails {
    return CastDetails(
        id = id,
        crew = crew.map { it.toDomain() },
        cast = cast.map { it.toDomain() }
    )
}
