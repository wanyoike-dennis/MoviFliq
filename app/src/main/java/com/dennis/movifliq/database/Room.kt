package com.dennis.movifliq.database

import android.content.Context
import androidx.room.*



@Database(entities = [MovieDatabase::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract val movieDao : MovieDao

    companion object{
        @Volatile
        private  var INSTANCE: AppDatabase? = null
        fun getDatabase(context:Context) : AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movieDatabase"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}



