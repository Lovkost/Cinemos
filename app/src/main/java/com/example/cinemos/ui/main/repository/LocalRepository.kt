package com.example.cinemos.ui.main.repository
interface LocalRepository {
    fun getAllHistory(): List<String>
    fun saveEntity(weather: String)
}