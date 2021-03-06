package com.lazymindapps.movies.model

data class Torrent(
    val date_uploaded: String,
    val date_uploaded_unix: Int,
    val hash: String,
    val peers: Int,
    val quality: String,
    val seeds: Int,
    val size: String,
    val size_bytes: Int,
    val type: String,
    val url: String
)