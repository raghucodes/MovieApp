package com.example.movieapp.domain.usecases

import com.example.movieapp.data.repository.MovieRepository

class SearchMoviesUseCase(private val repository: MovieRepository) {
    fun invoke(searchString : String) = repository.searchMovies(searchString)
}