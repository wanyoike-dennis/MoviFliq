package com.dennis.movifliq.viewmodels
import androidx.lifecycle.ViewModel
import com.dennis.movifliq.data.Movie
import com.dennis.movifliq.network.OmdbApi
import com.dennis.movifliq.utils.o_apikey


class SearchViewModel : ViewModel() {



      suspend fun searchMovies(query:String):List<Movie>{
            val response = OmdbApi.retrofitService.searchMovies(query, o_apikey)
            if (response.response == "True"){
             //   moviesResult.postValue(response.movies)
                return response.movies
            }
        else
            {
                throw Exception("Error searching for movies: ${response.response}")
            }

    }

}