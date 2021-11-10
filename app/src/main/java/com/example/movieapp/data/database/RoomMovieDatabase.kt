package com.example.movieapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.data.dao.MovieDao
import com.example.movieapp.domain.entities.MovieEntity

@Database(entities = [MovieEntity::class],version = 2)
abstract class RoomMovieDatabase : RoomDatabase(){
    abstract fun movieDao() : MovieDao

    companion object {
        @Volatile
        private var INSTANCE: RoomMovieDatabase? = null

        fun getDatabase(
            context: Context
        ): RoomMovieDatabase {

            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomMovieDatabase::class.java,
                        "movie_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    instance
                }

        }
    }
}