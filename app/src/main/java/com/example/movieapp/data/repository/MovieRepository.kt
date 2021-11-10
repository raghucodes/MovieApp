package com.example.movieapp.data.repository


import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.data.api.interfaces.MovieApi
import com.example.movieapp.data.api.models.MovieResponse
import com.example.movieapp.data.dao.MovieDao
import com.example.movieapp.domain.entities.MovieEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val movieApi: MovieApi,private val movieDao: MovieDao) {


    @WorkerThread
    fun getSavedMovies() : LiveData<List<MovieEntity>> = movieDao.getSavedMovies()

    @WorkerThread
    fun insertMovie(movieEntity: MovieEntity) { movieDao.insertMovie(movieEntity)}

    @WorkerThread
    fun deleteMovie(movieEntity: MovieEntity) { movieDao.deleteMovie(movieEntity) }

    @WorkerThread
    fun checkSaved(movieName : String) = movieDao.checkSaved(movieName)

    fun getPopularMovies() : LiveData<MovieResponse>{
        val popularMovies  = MutableLiveData<MovieResponse>()

        movieApi.getPopularMovies("IN").enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("failure","can't connect to api")
                Log.e("error",t.toString())
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.body()?.results != null) {
                    popularMovies.value = response.body()
                    Log.e("data",popularMovies.value.toString())
                }else{
                    Log.e("data","unable to retrieve data")
                }
            }
        })
        return popularMovies
    }

    fun getNowPlayingMovies() : LiveData<MovieResponse>{
        val nowPlayingMovies = MutableLiveData<MovieResponse>()
        movieApi.getNowPlayingMovies("IN").enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("failure","can't connect to api")
                Log.e("error",t.toString())
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

                if (response.body()?.results != null) {
                    nowPlayingMovies.value = response.body()
                    Log.e("data",nowPlayingMovies.value.toString())
                }
            }
        })
        return nowPlayingMovies
    }

    fun getTopRatedMovies() : LiveData<MovieResponse>{
        val topRatedMovies = MutableLiveData<MovieResponse>()
        movieApi.getTopRatedMovies("IN").enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("failure","can't connect to api")
                Log.e("error",t.toString())
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.body()?.results != null) {
                    topRatedMovies.value = response.body()
                    Log.e("data",topRatedMovies.value.toString())
                }
            }
        })
        return topRatedMovies
    }

    fun searchMovies(searchString : String) : LiveData<MovieResponse>{
        val searchMovies = MutableLiveData<MovieResponse>()
        movieApi.searchMovies(searchString).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("failure","can't connect to api")
                Log.e("error",t.toString())
            }
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
               if(response.body()?.results !=null){
                   searchMovies.value = response.body()
                   Log.e("search data",searchMovies.value.toString())
               }
            }
        })
        return searchMovies
    }

}