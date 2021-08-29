package com.example.cinemos.ui.main.repository

import com.example.cinemos.ui.main.model.MovieDTO

interface DetailsRepository {
    fun getMovieDetailsFromServer(
        genre:String,
        callback: retrofit2.Callback<MovieDTO>
    )
}