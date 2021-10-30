package com.example.flick.ui.fragments.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.HttpException
import com.example.domain.models.state.UiState
import com.example.domain.usecases.FetchMovieCast
import com.example.domain.usecases.FetchMovieDetails
import com.example.domain.usecases.FetchMovieReviews
import com.example.domain.usecases.FetchSimilarMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

class MovieDetailsViewModel(
    private val fetchMovieDetails: FetchMovieDetails,
    private val fetchMovieReviews: FetchMovieReviews,
    private val fetchMovieCast: FetchMovieCast,
    private val fetchSimilarMovies: FetchSimilarMovies
) : ViewModel() {
    private val _movieDetails = MutableStateFlow<UiState>(UiState.Loading)
    val movieDetails: StateFlow<UiState> get() = _movieDetails

    private val _movieReviews = MutableStateFlow<UiState>(UiState.Loading)
    val movieReviews: StateFlow<UiState> get() = _movieReviews

    private val _movieCast = MutableStateFlow<UiState>(UiState.Loading)
    val movieCast: StateFlow<UiState> get() = _movieCast

    private val _similarMovies = MutableStateFlow<UiState>(UiState.Loading)
    val similarMovies: StateFlow<UiState> get() = _similarMovies

    fun getMovieDetails(movieId: Int): MovieDetailsViewModel {
        viewModelScope.launch {
            try {
                fetchMovieDetails.invoke(movieId).collect {
                    _movieDetails.value = UiState.Success(it)
                }
            } catch (e: HttpException) {
                _movieDetails.value =
                    UiState.Error(e.localizedMessage ?: "Problem connecting to the internet")
            } catch (e: IOException) {
                _movieDetails.value =
                    UiState.Error(e.localizedMessage ?: "An unknown error occurred")
            }
        }
        return this
    }

    fun getMovieCast(movieId: Int): MovieDetailsViewModel {
        viewModelScope.launch {
            try {
                fetchMovieCast.invoke(movieId).collect {
                    _movieCast.value = UiState.Success(it.cast)
                }
            } catch (e: HttpException) {
                _movieCast.value =
                    UiState.Error(e.localizedMessage ?: "Problem connecting to the internet")
            } catch (e: IOException) {
                _movieCast.value =
                    UiState.Error(e.localizedMessage ?: "An unknown error occurred")
            }
        }
        return this
    }

    fun getMovieReviews(movieId: Int): MovieDetailsViewModel {
        viewModelScope.launch {
            try {
                fetchMovieReviews.invoke(movieId).collect {
                    _movieReviews.value = UiState.Success(it)
                }
            } catch (e: HttpException) {
                _movieReviews.value =
                    UiState.Error(e.localizedMessage ?: "Problem connecting to the internet")
            } catch (e: IOException) {
                _movieReviews.value =
                    UiState.Error(e.localizedMessage ?: "An unknown error occurred")
            }
        }
        return this
    }

    fun getSimilarMovies(movieId: Int): MovieDetailsViewModel {
        viewModelScope.launch {
            try {
                fetchSimilarMovies.invoke(movieId).collect {
                    _similarMovies.value = UiState.Success(it)
                }
            } catch (e: HttpException) {
                _similarMovies.value =
                    UiState.Error(e.localizedMessage ?: "Problem connecting to the internet")
            } catch (e: IOException) {
                _similarMovies.value =
                    UiState.Error(e.localizedMessage ?: "An unknown error occurred")
            }
        }
        return this
    }
}
