package com.example.movieapp.domain.usecases

import androidx.lifecycle.LiveData
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.domain.entities.MovieEntity

class GetSavedMoviesUseCase(private val repository: MovieRepository) {
    fun getSavedMovies() : LiveData<List<MovieEntity>> = repository.getSavedMovies()
}