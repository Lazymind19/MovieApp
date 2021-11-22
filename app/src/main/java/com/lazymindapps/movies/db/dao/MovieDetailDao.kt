package com.lazymindapps.movies.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lazymindapps.movies.model.Movie
import com.lazymindapps.movies.model.MovieDetail

@Dao
interface MovieDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieDetail: MovieDetail)


    @Query("Select * from tbl_movie_detail where id =:sn")
   suspend fun getSelectedMovie(sn: String): MovieDetail
}