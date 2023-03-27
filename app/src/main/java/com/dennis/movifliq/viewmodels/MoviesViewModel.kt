package com.dennis.movifliq.viewmodels

import androidx.lifecycle.*
import com.dennis.movifliq.data.Movies
import com.dennis.movifliq.network.MoviesApi
import com.dennis.movifliq.utils.apiKey
import kotlinx.coroutines.launch

enum class MoviesApiStatus{ LOADING,ERROR ,DONE}
class MoviesViewModel : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movies>>()


    private val _status = MutableLiveData<MoviesApiStatus>()
    val status:LiveData<MoviesApiStatus> = _status

    private fun getMovies(){
        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING
            try {
               val response = MoviesApi.retrofitService.getMovies(apiKey,1)
                val movies = response.body()?.result
                _moviesLiveData.postValue(movies!!)


                _status.value = MoviesApiStatus.DONE
            }
            catch (e :Exception){

                _moviesLiveData.value = listOf()
                _status.value = MoviesApiStatus.ERROR
            }

        }
    }

    init {
        getMovies()
    }
}
/*
class MoviesViewModelFactory() : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MoviesViewModel() as T
        }
        throw IllegalArgumentException("unknown viewModel class")
    }
}

 */