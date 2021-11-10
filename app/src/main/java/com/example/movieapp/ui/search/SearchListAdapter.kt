package com.example.movieapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.data.api.models.MovieResult
import com.example.movieapp.domain.entities.MovieEntity
import com.example.movieapp.ui.moviedetails.MovieDetailsFragment
import com.squareup.picasso.Picasso

class SearchListAdapter : RecyclerView.Adapter<SearchListAdapter.MyViewHolder>() {
    val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original"
    private var searchMoviesList: List<MovieResult> = ArrayList()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var movieName = itemView.findViewById<TextView>(R.id.movie_title)
        val posterUrl = itemView.findViewById<ImageView>(R.id.photo_url)
        val overview = itemView.findViewById<TextView>(R.id.movie_overview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.search_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return searchMoviesList.size
    }

    override fun onBindViewHolder(holder: SearchListAdapter.MyViewHolder, position: Int) {

        holder.movieName.text = searchMoviesList[position].title
        holder.overview.text = searchMoviesList[position].overview
        val url = BASE_IMAGE_URL+searchMoviesList[position].poster_path
        if(searchMoviesList[position].poster_path != null)
            Picasso.get().load(url).into(holder.posterUrl)
        else
            Picasso.get().load("https://www.pikpng.com/pngl/m/16-168770_user-iconset-no-profile-picture-icon-circle-clipart.png").into(holder.posterUrl)

        holder.posterUrl.setOnClickListener{
            val movieEntity = MovieEntity(searchMoviesList[position].poster_path,searchMoviesList[position].title,searchMoviesList[position].vote_average,searchMoviesList[position].overview)
            openFragment(it,movieEntity)
        }
    }

    fun updateList(movieList: List<MovieResult>){
        searchMoviesList = movieList
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