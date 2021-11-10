package com.example.movieapp.data.api.models.toprated

data class TopRatedMovieResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)