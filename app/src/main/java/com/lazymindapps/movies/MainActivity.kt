package com.lazymindapps.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.lazymindapps.movies.databinding.ActivityMainBinding
import com.lazymindapps.movies.databinding.FragmentMovieDetailBinding
import com.lazymindapps.movies.model.Movie
import com.lazymindapps.movies.model.MovieDetail
import com.lazymindapps.movies.model.Movy
import com.lazymindapps.movies.viewModel.MovieViewModel
import com.lazymindapps.movies.viewModel.MovieViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    val viewModel: MovieViewModel by viewModels {
        MovieViewModelFactory((application as MovieApplication).repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        //observing and saving movies in both movie and movie detail table

        viewModel.movies.observe(this, Observer {
            Log.d("MOVIE LIST CHECKING",it.toString())

           val movieAPI = it
            val data = it.data
            val movieList : List<Movy> = data.movies
            var movie:Movie
            var movieDetail : MovieDetail
            for (item in movieList){
                val sn = item.id
                val title = item.title
                val rating = item.rating
                val imageUrl = item.medium_cover_image
                val description = item.description_full
                val year = item.year

                movie = Movie(sn,title,rating.toString(),imageUrl)
                movieDetail = MovieDetail(sn,title,rating.toString(),description,imageUrl,year.toString())
                runBlocking {
                    withContext(Dispatchers.IO){

                            viewModel.insertMovie(movie)
                        viewModel.insertMovieDetail(movieDetail)



                    }
                }




            }
            Log.d("checking ", movieList.toString())




        })

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        navController = navHostFragment.findNavController()
        setSupportActionBar(binding.toolbar)

        setupActionBarWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}