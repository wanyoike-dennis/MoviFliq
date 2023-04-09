package com.dennis.movifliq.data

import com.google.gson.annotations.SerializedName



data class Movies(
   @SerializedName("original_title") val title: String,
   @SerializedName("overview") val overview:String,
   @SerializedName("poster_path") val posterPath:String,
   @SerializedName("release_date") val releaseDate:String,
   @SerializedName("vote_average") val voteAverage: Float,
   @SerializedName("vote_count") val voteCount:Int
)
