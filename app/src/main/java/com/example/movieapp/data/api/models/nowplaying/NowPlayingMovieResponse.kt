package com.example.movieapp.data.api.models.nowplaying

data class NowPlayingMovieResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)