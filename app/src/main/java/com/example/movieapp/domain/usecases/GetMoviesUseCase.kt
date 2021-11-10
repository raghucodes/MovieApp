package com.example.movieapp.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.movieapp.data.api.models.MovieResponse
import retrofit2.Call

class GetMoviesUseCase(private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
                       private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
                       private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase){

    fun getNowPlayingMovies() = getNowPlayingMoviesUseCase.invoke()
    fun getPopularMovies() = getPopularMoviesUseCase.invoke()
    fun getTopRatedMovies() = getTopRatedMoviesUseCase.invoke()

}