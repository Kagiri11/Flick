package com.example.domain.di

import com.example.domain.usecases.FetchMovieDetails
import com.example.domain.usecases.FetchMovieReviews
import com.example.domain.usecases.FetchNowPlaying
import com.example.domain.usecases.FetchPopularMovies
import com.example.domain.usecases.FetchTopRatedMovies
import com.example.domain.usecases.FetchUpcomingMovies
import org.koin.dsl.module

val domainModule = module {
    single { FetchMovieDetails(get()) }
    single { FetchMovieReviews(get()) }
    single { FetchNowPlaying(get()) }
    single { FetchPopularMovies(get()) }
    single {
        FetchTopRatedMovies(get())
        FetchUpcomingMovies(get())
    }
}
