package com.dennis.movifliq.viewmodels


import androidx.lifecycle.*
import com.dennis.movifliq.data.Movies
import com.dennis.movifliq.network.MoviesApi
import com.dennis.movifliq.utils.apiKey
import kotlinx.coroutines.launch

enum class MoviesApiStatus { LOADING, ERROR, DONE }
class MoviesViewModel : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movies>>()
    val moviesLiveData: LiveData<List<Movies>> = _moviesLiveData

    private val _status = MutableLiveData<MoviesApiStatus>()
    val status: LiveData<MoviesApiStatus> = _status

    private fun getMovies() {
        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING
            try {
                val response = MoviesApi.retrofitService.getMovies(apiKey, 1)
                if (response.isSuccessful) {
                    _moviesLiveData.value = response.body()?.results
                    _status.value = MoviesApiStatus.DONE
                }
            } catch (e: Exception) {
                _moviesLiveData.value = listOf()
                _status.value = MoviesApiStatus.ERROR
            }

        }
    }

    init {
        getMovies()
    }
}