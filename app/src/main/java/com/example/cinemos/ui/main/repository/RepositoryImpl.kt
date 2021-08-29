package com.example.cinemos.ui.main.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cinemos.ui.main.model.Model
import com.example.cinemos.ui.main.model.MovieDTO

class RepositoryImpl : Repository {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun getMovieFromServer(): MovieDTO {
        return Model().loadMovie()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getMovieFromLocal(): MovieDTO {
        return Model().loadMovie()
    }
}