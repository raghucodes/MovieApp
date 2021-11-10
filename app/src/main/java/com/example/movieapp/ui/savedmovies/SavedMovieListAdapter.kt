package com.example.movieapp.ui.savedmovies

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.domain.entities.MovieEntity
import com.example.movieapp.ui.moviedetails.MovieDetailsFragment
import com.squareup.picasso.Picasso

class SavedMovieListAdapter(private val context: Context,private val savedMoviesViewModel : SavedMoviesViewModel):RecyclerView.Adapter<SavedMovieListAdapter.MyViewHolder>() {
    val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original"
    private var movies : List<MovieEntity> = ArrayList()

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val url = itemView.findViewById<ImageView>(R.id.url)
        val movie = itemView.findViewById<TextView>(R.id.movie)
        val overview = itemView.findViewById<TextView>(R.id.overview)
        val saved = itemView.findViewById<ImageButton>(R.id.saved)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.saved_movie_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.e("saved movies count",movies.size.toString())
        return movies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.movie.text = movies[position].movieName
        holder.overview.text = movies[position].overview
        val imageUrl = BASE_IMAGE_URL+movies[position].url
        Picasso.get().load(imageUrl).into(holder.url)
        holder.url.setOnClickListener {
            openFragment(it,movies[position])
        }
        holder.saved.setOnClickListener {
            savedMoviesViewModel.deleteMovie(movies[position])
        }
    }

    fun updateJokeList(moviesList : List<MovieEntity>){
        movies = moviesList
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