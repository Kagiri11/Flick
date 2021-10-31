package com.example.repository.mappers

import com.example.cache.models.AuthorEntityDetails
import com.example.cache.models.ReviewEntity
import com.example.cache.models.ReviewsCacheResponse
import com.example.cache.models.popularmovies.PopularMovieEntity
import com.example.cache.models.topRatedMovies.NowPlayingMovieEntity
import com.example.cache.models.upcomingmovies.UpcomingMovieEntity
import com.example.cache.models.upcomingmovies.UpcomingMovieResponseEntity
import com.example.domain.models.Movie
import com.example.domain.models.MoviesResponse
import com.example.domain.models.reviews.AuthorDetails
import com.example.domain.models.reviews.Review
import com.example.domain.models.reviews.ReviewsResponse

fun ReviewsResponse.toEntity(): ReviewsCacheResponse {
    return ReviewsCacheResponse(
        movieId = id,
        page = page,
        results = results.map { it.toEntity() }
    )
}

fun MoviesResponse.toEntity(): UpcomingMovieResponseEntity {
    return UpcomingMovieResponseEntity(
        page = page,
        results = results.map { it.toEntity() }
    )
}

fun Movie.toEntity(): UpcomingMovieEntity {
    return UpcomingMovieEntity(
        id, title, posterPath, releaseDate, overview, voteAverage
    )
}

fun Movie.toPopularEntity(): PopularMovieEntity {
    return PopularMovieEntity(
        id, title, posterPath, releaseDate, overview, voteAverage
    )
}

fun Movie.toTopRatedEntity(): NowPlayingMovieEntity {
    return NowPlayingMovieEntity(
        id, title, posterPath, releaseDate, overview, voteAverage
    )
}

fun AuthorDetails.toEntity(): AuthorEntityDetails {
    return AuthorEntityDetails(
        avatar_path, name, rating, username
    )
}

fun Review.toEntity(): ReviewEntity {
    return ReviewEntity(
        id = id,
        author = author,
        author_details = author_details.toEntity(),
        content = content,
        created_at = created_at,
        updated_at = updated_at,
        url = url
    )
}
