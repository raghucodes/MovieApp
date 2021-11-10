package com.example.movieapp.ui.savedmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.entities.MovieEntity
import com.example.movieapp.domain.usecases.DeleteSavedMovieUseCase
import com.example.movieapp.domain.usecases.GetSavedMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedMoviesViewModel(private val getSavedMoviesUseCase: GetSavedMoviesUseCase,private val deleteSavedMoviesUseCase : DeleteSavedMovieUseCase) : ViewModel(){

    fun deleteMovie(movieEntity: MovieEntity) = viewModelScope.launch (Dispatchers.IO){
        deleteSavedMoviesUseCase.deleteSavedMovie(movieEntity)
    }

    fun getSavedMovies() : LiveData<List<MovieEntity>> {
        return getSavedMoviesUseCase.getSavedMovies()
    }

}