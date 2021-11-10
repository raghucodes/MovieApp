package com.example.movieapp.data.api.models

import com.example.movieapp.data.api.models.nowplaying.Result


data class MovieResponse (
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)