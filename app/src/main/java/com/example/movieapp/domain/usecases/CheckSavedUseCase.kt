package com.example.movieapp.domain.usecases

import com.example.movieapp.data.repository.MovieRepository

class CheckSavedUseCase(private val repository: MovieRepository) {
    fun checkSaved(movieName : String) = repository.checkSaved(movieName)
}