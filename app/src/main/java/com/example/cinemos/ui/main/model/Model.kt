package com.example.cinemos.ui.main.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

const val API_KEY = "80532f6339bb492b1aa7aec6675487b2"

class Model {

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadMovie() :MovieDTO {
        lateinit var movieDTO: MovieDTO

        try {
            val uri =
                URL("https://api.themoviedb.org/3/movie/now_playing?api_key=$API_KEY&language=ru-RU")
             var potok: Thread = Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    movieDTO =
                        Gson().fromJson(getLines(bufferedReader), MovieDTO::class.java)
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()

                } finally {
                    urlConnection.disconnect()
                }

            })
            potok.start()
            potok.join()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            //Обработка ошибки
        }
return movieDTO
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}

