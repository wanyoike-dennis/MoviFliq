package com.dennis.movifliq

import android.app.Application
import com.dennis.movifliq.database.AppDatabase

class MovieApplication : Application() {
    val database : AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
}