package com.lazymindapps.movies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.lazymindapps.movies.MainActivity
import com.lazymindapps.movies.R
import com.lazymindapps.movies.databinding.FragmentMovieDetailBinding
import com.lazymindapps.movies.model.MovieDetail
import com.lazymindapps.movies.viewModel.MovieViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieDetailFragment : Fragment(),CoroutineScope {

    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var viewModel: MovieViewModel
    private val args: MovieDetailFragmentArgs by navArgs()

    var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        fetchMovieDetailFromDb(args.sn.toString())

    }

    private fun fetchMovieDetailFromDb(id:String) {

        launch {
            val selectedMovie: MovieDetail = viewModel.getSelectedMovie(id)

            if (selectedMovie != null) {

                binding.tvDescription.text = selectedMovie.description
                binding.tvRating.text = "Rating : ${selectedMovie.rating}"
                binding.tvTitle.text = selectedMovie.title
                binding.tvYear.text = "Year : ${selectedMovie.year}"
                Picasso.get().load(selectedMovie.imageUrl).error(R.drawable.ic_launcher_background)
                    .into(binding.ivImage)

            }


        }
    }

}