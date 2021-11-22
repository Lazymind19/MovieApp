package com.lazymindapps.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lazymindapps.movies.R
import com.lazymindapps.movies.databinding.RvMovieListItemBinding
import com.lazymindapps.movies.model.Movie
import com.lazymindapps.movies.ui.MovieListFragmentDirections
import com.squareup.picasso.Picasso

class MovieListAdapter(val movieList: List<Movie>) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {

        val binding = RvMovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        val movie = movieList[position]
        val sn = position+1
        holder.bind(movie,sn)

        holder.itemView.setOnClickListener {
            val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie.id.toString())
            it.findNavController().navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(val itemBinding: RvMovieListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movie: Movie,sn:Int) {
            itemBinding.tvId.text = sn.toString()
            itemBinding.tvRating.text ="Rating: ${movie.rating}"
            itemBinding.tvTitle.text = movie.title
           Picasso.get().load(movie.imageUrl).placeholder(R.drawable.ic_launcher_background)
               .error(R.drawable.ic_launcher_background).into(itemBinding.ivImage)

        }
    }
}