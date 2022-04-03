package com.example.cinemos.ui.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieData(
    val title: String,
    val rating: String,
    val budget: String,
    val description: String
):Parcelable

fun getMovie():List<MovieData> {
  return  listOf(
        MovieData("Голгофа", "7.5", "1500000", "Про священника"),
        MovieData("Три билборда", "8.5", "1600000", "Про историю матери,чью дочь убили"),
        MovieData("Шоу Трумана","10.0","10000000","Джим Керри в серьезной, драматичной роли")

    )
}