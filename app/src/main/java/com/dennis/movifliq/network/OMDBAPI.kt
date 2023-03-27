package com.dennis.movifliq.network

import com.dennis.movifliq.data.ApiResponse
import com.dennis.movifliq.utils.O_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory.*
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .baseUrl(O_BASE_URL)
    .addConverterFactory(create())
    .build()

interface OmdpApiService{
    @GET("/")
    suspend fun searchMovies(
        @Query("s") query: String,
        @Query("apikey") apiKey:String
    ): ApiResponse
}

object OmdbApi{
    val retrofitService : OmdpApiService by lazy{
        retrofit.create(OmdpApiService::class.java)
    }
}