package com.example.cinemos.ui.main.repository

import com.example.cinemos.ui.main.model.MovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieAPI {
        @GET("3/movie")
        fun getWeather(
            @Header("api_key") token: String,
            @Query("now_playing") genre: String,
        ): Call<MovieDTO>

}