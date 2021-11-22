package com.lazymindapps.movies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lazymindapps.movies.model.Movie
import com.lazymindapps.movies.model.MovieApi
import com.lazymindapps.movies.model.MovieDetail
import com.lazymindapps.movies.repo.MovieRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(val repo:MovieRepo) :ViewModel() {

    init {
        viewModelScope.launch (Dispatchers.IO){
            repo.getMovies()

        }
    }

    val movies : LiveData<MovieApi>
    get() = repo.moviesApi


    var allMovieList : LiveData<List<Movie>> ?= null
    var selectedMovie :MovieDetail ?= null

    suspend fun insertMovie(movie: Movie) =repo.insertMovie(movie)


    fun getAllMovies() :LiveData<List<Movie>>{
        allMovieList = repo.getAllMovies()
        return allMovieList as LiveData<List<Movie>>
    }


    suspend fun insertMovieDetail(movieDetail: MovieDetail) = repo.insertMovieDetail(movieDetail)

   suspend fun getSelectedMovie(sn:String) : MovieDetail {
        selectedMovie = repo.getSelectedMovie(sn)
        return selectedMovie as MovieDetail


    }
}