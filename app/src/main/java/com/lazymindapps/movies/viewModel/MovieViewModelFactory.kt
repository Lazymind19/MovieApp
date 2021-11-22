package com.lazymindapps.movies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lazymindapps.movies.repo.MovieRepo

class MovieViewModelFactory(val repo:MovieRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(repo) as T

    }
}