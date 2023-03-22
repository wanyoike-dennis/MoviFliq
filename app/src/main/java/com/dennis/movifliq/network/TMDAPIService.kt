package com.dennis.movifliq.network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL ="https://api.themoviedb.org/3/movie/550?api_key=7416254b7cecbf25691f71f27ab7ee7c"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TMDAPIService{
    @GET("")
    suspend fun getMovies():List<Movies>
}

object MoviesApi{
    val retrofitService :TMDAPIService by lazy {
        retrofit.create(TMDAPIService::class.java)
    }
}
