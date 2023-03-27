package com.dennis.movifliq.data

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("search") val movies:List<Movie>,
    @SerializedName("totalResults") val totalResults:Int,
    @SerializedName("Response") val response:String
)
