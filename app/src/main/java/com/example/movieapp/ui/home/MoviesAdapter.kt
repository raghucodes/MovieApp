package com.example.movieapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.domain.entities.MovieEntity
import com.example.movieapp.ui.moviedetails.MovieDetailsFragment
import com.squareup.picasso.Picasso

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original"
    private var moviesList: ArrayList<MovieEntity> = ArrayList()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var movieName = itemView.findViewById<TextView>(R.id.MovieName)
        val posterUrl = itemView.findViewById<ImageView>(R.id.PosterUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.e("count",moviesList.size.toString())
       return moviesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val movie : MovieEntity = moviesList[position]
        holder.movieName.text = movie.movieName
        val url = BASE_IMAGE_URL+movie.url
        if(movie.url != null)
            Picasso.get().load(url).into(holder.posterUrl)

        holder.posterUrl.setOnClickListener{
            openFragment(it,moviesList[position])
        }
    }

    fun updateMovies(movies : ArrayList<MovieEntity>){
        moviesList.clear()
        moviesList = movies
        Log.e("updated count",moviesList.size.toString())
        notifyDataSetChanged()
    }

    fun openFragment(view: View,movie : MovieEntity){
        val fragment : Fragment =
            MovieDetailsFragment()
        val args : Bundle = Bundle()
        args.putParcelable("movieobject",movie)
        fragment.arguments = args
        val appCompatActivity : AppCompatActivity = view.context as AppCompatActivity
        appCompatActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack(null)
            .commit()
    }
}