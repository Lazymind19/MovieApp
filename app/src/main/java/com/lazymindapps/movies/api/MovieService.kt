package com.lazymindapps.movies.api

import com.lazymindapps.movies.model.Movie
import com.lazymindapps.movies.model.MovieApi
import retrofit2.Response
import retrofit2.http.GET


interface MovieService{



 @GET("api/v2/list_movies.json")
 suspend fun getMovie() : Response<MovieApi>


}