package com.dennis.movifliq

import com.dennis.movifliq.network.MoviesApi
import com.dennis.movifliq.utils.apiKey
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Test

class NewTest {

    private val apiService = MoviesApi.retrofitService

    @Test
    fun  getCharacters(){
        runBlocking {

            // Make the API call to get the list of characters
            val response = apiService.getMovies(apiKey,1)

            // Check that the response is successful
            assertThat(response.isSuccessful).isTrue()

            //Check that the response body is not null
            val movies = response.body()?.results
            assertThat(movies).isNotNull()

            // check that the list of characters is not empty
            assertThat(movies?.isNotEmpty()).isTrue()

        }
    }
}