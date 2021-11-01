package com.example.flick.ui.fragments.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.HttpException
import com.example.domain.models.state.UiState
import com.example.domain.usecases.FetchNowPlaying
import com.example.domain.usecases.FetchPopularMovies
import com.example.domain.usecases.FetchUpcomingMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

class MoviesViewModel(
    private val fetchNowPlayingMovies: FetchNowPlaying,
    private val fetchUpcomingMovies: FetchUpcomingMovies,
    private val fetchPopularMovies: FetchPopularMovies
) : ViewModel() {

    private var upcomingMoviesPage = 2

    init {
        getNowPlayingMovies()
        getPopularMovies()
        getUpcomingMovies()
    }

    private val _nowPlayingMovies = MutableStateFlow<UiState>(UiState.Loading)
    val nowPlayingMovies: StateFlow<UiState> = _nowPlayingMovies

    private val _upcomingMovies = MutableStateFlow<UiState>(UiState.Loading)
    val upcomingMovies: StateFlow<UiState> = _upcomingMovies

    private val _popularMovies = MutableStateFlow<UiState>(UiState.Loading)
    val popularMovies: StateFlow<UiState> = _popularMovies

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            try {
                fetchNowPlayingMovies().collect { nowPlaying ->
                    _nowPlayingMovies.value = UiState.Success(nowPlaying)
                }
            } catch (e: HttpException) {
                _nowPlayingMovies.value =
                    UiState.Error(e.localizedMessage ?: "Problem connecting to the internet")
            } catch (e: IOException) {
                _nowPlayingMovies.value =
                    UiState.Error(e.localizedMessage ?: "An unknown error occurred")
            }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            try {
                fetchUpcomingMovies(upcomingMoviesPage).collect { nowPlaying ->
                    _upcomingMovies.value = UiState.Success(nowPlaying)
                }
            } catch (e: HttpException) {
                _upcomingMovies.value =
                    UiState.Error(e.localizedMessage ?: "Problem connecting to the internet")
            } catch (e: IOException) {
                _upcomingMovies.value =
                    UiState.Error(e.localizedMessage ?: "An unknown error occurred")
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            try {
                fetchPopularMovies().collect { nowPlaying ->
                    _popularMovies.value = UiState.Success(nowPlaying)
                    Log.i("Movies ViewModel", "$nowPlaying")
                }
            } catch (e: HttpException) {
                _popularMovies.value =
                    UiState.Error(e.localizedMessage ?: "Problem connecting to the internet")
            } catch (e: IOException) {
                _popularMovies.value =
                    UiState.Error(e.localizedMessage ?: "An unknown error occurred")
            }
        }
    }
}
