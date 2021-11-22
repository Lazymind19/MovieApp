package com.lazymindapps.movies.model

data class MovieApi(
    val meta: Meta,
    val `data`: Data,
    val status: String,
    val status_message: String
)