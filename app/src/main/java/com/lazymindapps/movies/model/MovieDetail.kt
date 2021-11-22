package com.lazymindapps.movies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_movie_detail")
data class MovieDetail (
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    var title:String,
    var rating:String,
    var description : String,
    var imageUrl:String,
    var year:String

        )