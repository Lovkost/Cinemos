package com.example.cinemos.ui.main.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemos.ui.main.app.App.Companion.getHistoryDao
import com.example.cinemos.ui.main.repository.LocalRepository
import com.example.cinemos.ui.main.repository.LocalRepositoryImpl
import com.example.cinemos.ui.main.repository.Repository
import com.example.cinemos.ui.main.repository.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl(),

    ) :
    ViewModel() {
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())

    fun getLiveData() = liveDataToObserve

    @RequiresApi(Build.VERSION_CODES.M)
    fun getMovie() = getDataFromLocalSource()

    @RequiresApi(Build.VERSION_CODES.M)
    fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(repositoryImpl.getMovieFromLocal()))
        }.start()
    }
    fun saveCityToDB(weather: String) {
        historyRepository.saveEntity(weather)
    }
    }
