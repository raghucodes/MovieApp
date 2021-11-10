package com.example.movieapp.ui.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.entities.MovieEntity
import com.example.movieapp.domain.usecases.CheckSavedUseCase
import com.example.movieapp.domain.usecases.GetSavedMoviesUseCase
import com.example.movieapp.domain.usecases.InsertSavedMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val insertSavedMovieUseCase: InsertSavedMovieUseCase,
                            private val checkSavedUseCase: CheckSavedUseCase):ViewModel() {
    fun insertSavedMovies(movieEntity: MovieEntity) = viewModelScope.launch(Dispatchers.IO){ insertSavedMovieUseCase.insertMovie(movieEntity) }
    fun checkSaved(movieName:String) =  checkSavedUseCase.checkSaved(movieName)
}