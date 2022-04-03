package com.example.cinemos.ui.main.repository

import com.example.cinemos.ui.main.room.HistoryDao
import com.example.cinemos.ui.main.room.HistoryEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDao) :
    LocalRepository {

    override fun getAllHistory(): List<String> {
        return convertHistoryEntityToWeather(localDataSource.all())
    }

    override fun saveEntity(weather: String) {
        localDataSource.insert(convertWeatherToEntity(weather))
    }

    fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<String> {
        return entityList.map {
            it.description
        }
    }

    fun convertWeatherToEntity(weather: String): HistoryEntity {
        return HistoryEntity(0,weather)
    }
}