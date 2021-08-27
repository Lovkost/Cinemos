package com.example.cinemos.ui.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FactDTO(
    val original_title: String?,
    val popularity: String?,
    val budget: String?,
    val overview:String?,
//    val poster_path:String?
) : Parcelable
