package com.lazymindapps.movies

import android.app.Application
import com.lazymindapps.movies.api.MovieService
import com.lazymindapps.movies.api.RetrofitHelper
import com.lazymindapps.movies.db.MovieDatabase
import com.lazymindapps.movies.repo.MovieRepo

class MovieApplication : Application() {
    val database by lazy {
        MovieDatabase.createDatabase(this)
    }
    val retrofit by lazy{
        RetrofitHelper.getRetrofit().create(MovieService::class.java)
    }
    val repo by lazy { MovieRepo(database,retrofit) }
}