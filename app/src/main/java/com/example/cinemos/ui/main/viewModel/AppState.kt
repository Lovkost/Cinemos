package com.example.cinemos.ui.main.viewModel

import com.example.cinemos.ui.main.model.MovieData

sealed class AppState{
    data class Success(val movieData: MovieData): AppState()
    data class Error(val error:Throwable): AppState()
    object Loading: AppState()
}
