package com.example.flick.ui.fragments.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.HttpException
import com.example.domain.models.state.UiState
import com.example.domain.usecases.SearchMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

class SearchViewModel(
    private val searchMovies: SearchMovies
) : ViewModel() {
    private val _searchedMovies: MutableStateFlow<UiState> = MutableStateFlow(UiState.Error(""))
    val searchedMovies: StateFlow<UiState> get() = _searchedMovies

    fun searchMovies(query: String) {
        viewModelScope.launch {
            try {
                searchMovies.invoke(query).collect {
                    _searchedMovies.value = UiState.Success(it)
                }
            } catch (e: HttpException) {
                _searchedMovies.value = UiState.Error(e.localizedMessage ?: "Unable to connect to the internet")
            } catch (e: IOException) {
                _searchedMovies.value = UiState.Error(e.localizedMessage ?: "An unknown error occurred")
            }
        }
    }
}
