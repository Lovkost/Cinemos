package com.example.cinemos.ui.main.repository

import com.example.cinemos.ui.main.model.MovieDTO

interface Repository {
    fun getMovieFromServer(): MovieDTO
    fun getMovieFromLocal(): MovieDTO
}