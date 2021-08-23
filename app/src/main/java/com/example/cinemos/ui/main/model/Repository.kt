package com.example.cinemos.ui.main.model

interface Repository {
    fun getMovieFromServer():MovieDTO
    fun getMovieFromLocal():MovieDTO
}