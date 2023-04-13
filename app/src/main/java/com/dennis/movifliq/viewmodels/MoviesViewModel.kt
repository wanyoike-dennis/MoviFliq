package com.dennis.movifliq.viewmodels


import android.util.Log
import androidx.lifecycle.*
import com.dennis.movifliq.data.Movies
import com.dennis.movifliq.database.MovieDao
import com.dennis.movifliq.repository.TmdbMovieRepository
import com.dennis.movifliq.utils.apiKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class MoviesApiStatus { LOADING, ERROR, DONE }
class MoviesViewModel(
    private val movieRepository: TmdbMovieRepository,
    private val movieDao: MovieDao
    ) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<List<Movies>>()
    val moviesLiveData: LiveData<List<Movies>> = _moviesLiveData

    private val _status = MutableLiveData<MoviesApiStatus>()
    val status: LiveData<MoviesApiStatus> = _status

    private var currentPage = 1



    private fun String.getMovie(apiKey:String,page: Int){

        _status.value = MoviesApiStatus.LOADING
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val moviesFromDb = movieRepository.getMoviesFromDb()
                try {
                    if (moviesFromDb.isEmpty()){
                        val moviesFromApi = movieRepository.getMoviesFromApi(apiKey,page)
                        movieRepository.cacheMovies(moviesFromApi)
                        _moviesLiveData.postValue(moviesFromApi)
                        currentPage ++
                        _status.value = MoviesApiStatus.DONE
                    }
                    else{
                        _moviesLiveData.postValue(moviesFromDb)
                        _status.value = MoviesApiStatus.DONE
                    }
                } catch (e:Exception){
                    Log.e(TAG,"failed to fetch movies",e)
                    _status.postValue(MoviesApiStatus.ERROR)
                }

            }
        }
    }
    companion object{
        private const val TAG = "MovieViewModel"
    }

    init {
        apiKey.getMovie(apiKey,1)
    }


}
@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(
    private val movieRepository: TmdbMovieRepository,
    private val movieDao: MovieDao
    ):
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(movieRepository,movieDao) as T
        }
        throw IllegalArgumentException("unknown viewModel class")
    }
}