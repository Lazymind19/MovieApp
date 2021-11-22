package com.lazymindapps.movies.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lazymindapps.movies.api.MovieService
import com.lazymindapps.movies.db.MovieDatabase
import com.lazymindapps.movies.model.Movie
import com.lazymindapps.movies.model.MovieApi
import com.lazymindapps.movies.model.MovieDetail

class MovieRepo(private val db:MovieDatabase, private val movieService: MovieService) {
    //All api functions
    private val movieLiveData =  MutableLiveData<MovieApi>()

    val moviesApi : LiveData<MovieApi>
    get() = movieLiveData

    suspend fun getMovies(){
        val result  = movieService.getMovie()
        if (result?.body() !=null){

            movieLiveData.postValue(result.body())
            //inserting into database
            val movie = result.body()


        }
    }



    //All db functions
    suspend fun insertMovie(movie:Movie) = db.getMovieDao().insertMovie(movie)
    fun getAllMovies():LiveData<List<Movie>> = db.getMovieDao().getAllMovies()

    suspend fun insertMovieDetail(movieDetail: MovieDetail) = db.getMovieDetailDao().insertMovie(movieDetail)

  suspend  fun getSelectedMovie(sn:String):MovieDetail = db.getMovieDetailDao().getSelectedMovie(sn)

}