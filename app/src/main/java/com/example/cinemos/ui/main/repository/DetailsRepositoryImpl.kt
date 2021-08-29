package com.example.cinemos.ui.main.repository

import com.example.cinemos.ui.main.model.MovieDTO
import com.example.cinemos.ui.main.model.RemoteDataSource
import retrofit2.Callback

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    DetailsRepository {

    override fun getMovieDetailsFromServer(
        genre: String,
        callback: retrofit2.Callback<MovieDTO>
    ) {
        remoteDataSource.getWeatherDetails(genre, callback)
    }
}