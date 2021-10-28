package com.example.domain.models.state

import com.example.domain.models.Movie

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<Movie>) : UiState()
    data class Error(val error: String) : UiState()
}
