package com.example.cinemos.ui.main.viewModel

import com.example.cinemos.ui.main.model.MovieDTO

sealed class AppState{
    data class Success(val movieData: MovieDTO): AppState()
    data class Error(val error:Throwable): AppState()
    object Loading: AppState()
}
