package com.lazymindapps.movies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lazymindapps.movies.MainActivity
import com.lazymindapps.movies.R
import com.lazymindapps.movies.adapter.MovieListAdapter
import com.lazymindapps.movies.databinding.FragmentMovieListBinding
import com.lazymindapps.movies.model.Movie
import com.lazymindapps.movies.viewModel.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


class MovieListFragment : Fragment(), CoroutineScope {

    private lateinit var binding: FragmentMovieListBinding
    lateinit var viewModel: MovieViewModel
    var movieList: List<Movie> = mutableListOf()

    var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        fetchAllMoviesFromDb()
    }

    private fun fetchAllMoviesFromDb() {
        viewModel.getAllMovies().observe(viewLifecycleOwner, Observer { movies ->

            if (movies != null) {
                movieList = movies
                showMovieListRecyclerView(movieList)


            }

        })
    }

    private fun showMovieListRecyclerView(lists: List<Movie>) {
       // val layoutManager = LinearLayoutManager(context)
        val layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.rvMovieList.layoutManager = layoutManager
        val adapter = MovieListAdapter(lists)
        binding.rvMovieList.adapter = adapter
    }

}