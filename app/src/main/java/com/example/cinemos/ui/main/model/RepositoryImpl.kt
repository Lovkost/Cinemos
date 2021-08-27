package com.example.cinemos.ui.main.model

import android.media.Image
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cinemos.R

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