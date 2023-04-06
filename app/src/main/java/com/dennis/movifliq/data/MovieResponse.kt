package com.dennis.movifliq.data

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page:Int,
    val results: List<Movies>,
    @SerializedName("total_results") val totalResults:Int,
    @SerializedName("total_pages") val totalPages:Int
)
