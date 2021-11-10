package com.example.movieapp.ui.search

import androidx.lifecycle.ViewModel
import com.example.movieapp.domain.usecases.SearchMoviesUseCase

class SearchViewModel(private val searchMoviesUseCase: SearchMoviesUseCase):ViewModel() {
    fun searchMovies(searchString: String) = searchMoviesUseCase.invoke(searchString)
}