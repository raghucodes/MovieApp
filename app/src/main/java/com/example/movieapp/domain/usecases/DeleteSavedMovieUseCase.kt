package com.example.movieapp.domain.usecases

import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.domain.entities.MovieEntity

class DeleteSavedMovieUseCase(private val repository: MovieRepository) {
    suspend fun deleteSavedMovie(movieEntity: MovieEntity) = repository.deleteMovie(movieEntity)
}

