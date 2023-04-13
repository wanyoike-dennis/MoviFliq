package com.dennis.movifliq.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao{
    @Query("select * from movieDatabase")
    suspend fun getMovies(): List<MovieDatabase>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieDatabase>)

     @Query("delete from movieDatabase")
     suspend fun deleteAll()
}