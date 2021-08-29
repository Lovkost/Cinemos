package com.example.cinemos.ui.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemos.ui.main.model.FactDTO
import com.example.cinemos.ui.main.model.MovieDTO
import com.example.cinemos.ui.main.model.RemoteDataSource
import com.example.cinemos.ui.main.repository.DetailsRepository
import com.example.cinemos.ui.main.repository.DetailsRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class DetailsViewModel(
    private val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepositoryImpl: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource())
) : ViewModel() {

    fun getLiveData() = detailsLiveData

    fun getWeatherFromRemoteSource(genre:String) {
        detailsLiveData.value = AppState.Loading
        detailsRepositoryImpl.getMovieDetailsFromServer(genre, callBack)
    }

    private val callBack = object :
        Callback<MovieDTO> {

        override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
            val serverResponse: MovieDTO? = response.body()
            detailsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
            detailsLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        private fun checkResponse(serverResponse: MovieDTO): AppState {
            val fact = serverResponse.results
            return if (fact == null) {
                AppState.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppState.Success(convertDtoToModel(serverResponse))
            }
        }
        fun convertDtoToModel(weatherDTO: MovieDTO): MovieDTO {
            val fact: List<FactDTO?> = weatherDTO.results
            return weatherDTO
      }
    }
}