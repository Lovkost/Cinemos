package com.example.cinemos.ui.main.model

interface Repository {
    fun getMovieFromServer():MovieData
    fun getMovieFromLocal():List<MovieData>
}