package com.example.movieapp.domain.usecases

import com.example.movieapp.data.repository.MovieRepository

class GetPopularMoviesUseCase(private val repository: MovieRepository) {
    fun invoke() = repository.getPopularMovies()
}