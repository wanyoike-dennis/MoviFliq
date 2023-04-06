package com.dennis.movifliq.network

import com.dennis.movifliq.data.MovieResponse

import com.dennis.movifliq.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface TMDAPIService{
    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey:String,
        @Query("page") page:Int
    ): Response<MovieResponse>
}

object MoviesApi{
    val retrofitService :TMDAPIService by lazy {
        retrofit.create(TMDAPIService::class.java)
    }
}
