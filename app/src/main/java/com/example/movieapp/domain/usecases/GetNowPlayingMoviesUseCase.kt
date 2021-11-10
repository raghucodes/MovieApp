package com.example.movieapp.domain.usecases

import com.example.movieapp.data.repository.MovieRepository

class GetNowPlayingMoviesUseCase(private val repository: MovieRepository) {
    fun invoke() = repository.getNowPlayingMovies()
}