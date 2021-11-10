package com.example.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.api.models.MovieResponse
import com.example.movieapp.domain.entities.SectionEntity
import com.example.movieapp.domain.usecases.GetMoviesUseCase
import retrofit2.Call

class MoviesViewModel(private val getMoviesUseCase: GetMoviesUseCase):ViewModel() {

    fun getPopularMovies() = getMoviesUseCase.getPopularMovies()
    fun getTopRatedMovies() = getMoviesUseCase.getTopRatedMovies()
    fun getNowPlayingMovies() = getMoviesUseCase.getNowPlayingMovies()

}