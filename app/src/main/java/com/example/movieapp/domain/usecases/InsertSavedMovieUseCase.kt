package com.example.movieapp.domain.usecases

import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.domain.entities.MovieEntity

class InsertSavedMovieUseCase(private val repository: MovieRepository) {
    suspend fun insertMovie(movieEntity: MovieEntity){ repository.insertMovie(movieEntity) }
}