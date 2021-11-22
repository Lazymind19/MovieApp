package com.lazymindapps.movies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_movie")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    var id: Int,

    var title: String,
    var rating: String,
    var imageUrl: String,


    )