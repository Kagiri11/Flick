package com.example.flick.ui.di

import com.example.flick.ui.fragments.home.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MoviesViewModel(get(), get(), get()) }
}
