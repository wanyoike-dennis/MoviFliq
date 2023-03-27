package com.dennis.movifliq.data

import com.google.gson.annotations.SerializedName

data class Movies(
   val id: Int,
   val title: String,
   val overview:String,
   @SerializedName("poster_path") val posterPath:String,
   @SerializedName("release_date") val releaseDate:String
)
