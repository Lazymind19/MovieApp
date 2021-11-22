package com.lazymindapps.movies.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lazymindapps.movies.db.dao.MovieDao
import com.lazymindapps.movies.db.dao.MovieDetailDao
import com.lazymindapps.movies.model.Movie
import com.lazymindapps.movies.model.MovieDetail

@Database(entities = [Movie::class, MovieDetail::class], version = 4)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
    abstract fun getMovieDetailDao(): MovieDetailDao

    companion object {
        private var INSTANCE: MovieDatabase? = null
        fun createDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"

                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }

}