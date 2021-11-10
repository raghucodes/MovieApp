package com.example.movieapp.data.api.interfaces

import com.example.movieapp.data.api.models.MovieResponse
import com.example.movieapp.data.api.models.MovieResult
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.themoviedb.org/3/"
interface MovieApi {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("region") region : String) : Call<MovieResponse>

    @GET("movie/popular")
    fun getPopularMovies(@Query("region") region : String) : Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("region") region : String) : Call<MovieResponse>

    @GET("search/movie")
    fun searchMovies(@Query("query") query : String) : Call<MovieResponse>
}