package com.dennis.movifliq.repository

import com.dennis.movifliq.data.Movies
import com.dennis.movifliq.database.MovieDao
import com.dennis.movifliq.database.toMovie
import com.dennis.movifliq.database.toMovieEntity
import com.dennis.movifliq.network.MoviesApi
import retrofit2.HttpException

class TmdbMovieRepository(private val tmdbApiService:MoviesApi, private val movieDao: MovieDao){

    suspend fun getMoviesFromApi(apiKey:String,page:Int):List<Movies>{
        val response = tmdbApiService.retrofitService.getMovies(apiKey,page)
        if (response.isSuccessful){
            return response.body()?.results ?: emptyList()
            }
        else {
            throw HttpException(response)
        }
    }

    suspend fun getMoviesFromDb():List<Movies>{
        return movieDao.getMovies().map { it.toMovie() }
    }

    suspend fun cacheMovies(movies:List<Movies>){
        movieDao.deleteAll()
        val movieEntities = movies.map { it.toMovieEntity() }
        movieDao.insertMovies(movieEntities)
    }

    suspend fun getPopularMovies(apiKey:String,page:Int):List<Movies>{
        // try to fetch movie from the database
        val movieEntities = movieDao.getMovies()
        if (movieEntities.isNotEmpty()){
            return movieEntities.map { it.toMovie() }
        }

        // fallback to the network
        val response = tmdbApiService.retrofitService.getMovies(apiKey,page)
        if (response.isSuccessful){
            val movies = response.body()?.results ?: emptyList()
            movieDao.insertMovies(movies.map { it.toMovieEntity()})
            return movies
        }
        else {
            throw HttpException(response)
        }
    }
}