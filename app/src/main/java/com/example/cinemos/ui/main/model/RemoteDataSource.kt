package com.example.cinemos.ui.main.model

import com.example.cinemos.BuildConfig
import com.example.cinemos.ui.main.repository.MovieAPI
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    private val weatherApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(MovieAPI::class.java)

    fun getWeatherDetails(genre: String, callback: Callback<MovieDTO>) {
        weatherApi.getWeather(API_KEY, genre).enqueue(callback)
    }
}