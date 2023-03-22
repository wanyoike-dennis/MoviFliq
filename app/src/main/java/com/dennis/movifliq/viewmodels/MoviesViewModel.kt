package com.dennis.movifliq.viewmodels

import androidx.lifecycle.*
import com.dennis.movifliq.network.Movies
import com.dennis.movifliq.network.MoviesApi
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movies>>()
    val movies :LiveData<List<Movies>> = _movies

    private fun getMovies(){
        viewModelScope.launch {
            try {
                _movies.value = MoviesApi.retrofitService.getMovies()
            }
            catch (e :Exception){
                println(e)
            }
        }
    }

    init {
        getMovies()
    }
}
class MoviesViewModelFactory() : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MoviesViewModel() as T
        }
        throw IllegalArgumentException("unknown viewModel class")
    }
}