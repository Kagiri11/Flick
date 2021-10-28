package com.example.repository.di

import com.example.domain.repositories.MovieDomainRepository
import com.example.repository.sources.MovieDataRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieDomainRepository> { MovieDataRepository(get(), get()) }
}
