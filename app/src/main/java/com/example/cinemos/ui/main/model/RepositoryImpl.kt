package com.example.cinemos.ui.main.model

class RepositoryImpl:Repository {
    override fun getMovieFromServer(): MovieData {
    return MovieData("Aasd",7.4,2000,"asdsdsad")
    }

    override fun getMovieFromLocal(): List<MovieData> {
        return getMovie()
    }
}