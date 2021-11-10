package com.example.movieapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieapp.domain.entities.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies_table")
    fun getSavedMovies() : LiveData<List<MovieEntity>>

    @Delete
    fun deleteMovie(movieEntity: MovieEntity)

    @Query("SELECT movieName FROM movies_table WHERE movieName = :name")
    fun checkSaved(name : String) : LiveData<String>
}