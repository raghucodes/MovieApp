package com.example.movieapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.data.api.models.MovieResult
import com.example.movieapp.domain.entities.MovieEntity
import com.example.movieapp.domain.entities.SectionEntity
import com.example.movieapp.ui.context

class SectionListAdapter(private val sections : ArrayList<SectionEntity>): RecyclerView.Adapter<SectionListAdapter.MyViewHolder>() {

    private var nowPlaying : ArrayList<MovieEntity> = ArrayList()
    private var topRated : ArrayList<MovieEntity> = ArrayList()
    private var popular : ArrayList<MovieEntity> = ArrayList()
    private var movieListAdapter : ArrayList<MoviesAdapter> = ArrayList()
    private var recyclerView : ArrayList<RecyclerView> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movies_section,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return sections.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        movieListAdapter.add(MoviesAdapter())
       // holder.moviesList.adapter = MoviesAdapter()
        holder.moviesList.adapter = movieListAdapter[position]
        holder.moviesList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        holder.sectionName.text = sections[position].sectionName
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val sectionName = itemView.findViewById<TextView>(R.id.section_name)
        val moviesList = itemView.findViewById<RecyclerView>(R.id.Movies_List)
    }

    fun updateNowPlaying(movieResponse: List<MovieResult>){
        val list : ArrayList<MovieEntity> = ArrayList()
        for(i in movieResponse){
            if(i.poster_path!=null)
                list.add(MovieEntity(i.poster_path,i.title,i.vote_average,i.overview))
//            else
//                list.add(MovieEntity("https://www.pikpng.com/pngl/m/16-168770_user-iconset-no-profile-picture-icon-circle-clipart.png",i.title,i.vote_average,i.overview))
        }
        nowPlaying = list
        movieListAdapter[0].updateMovies(nowPlaying)
       // notifyDataSetChanged()
    }
    fun updateTopRated(movieResponse: List<MovieResult>){
        val list : ArrayList<MovieEntity> = ArrayList()
        for(i in movieResponse){
            if(i.poster_path!=null)
                list.add(MovieEntity(i.poster_path,i.title,i.vote_average,i.overview))
//            else
//                list.add(MovieEntity("https://www.pikpng.com/pngl/m/16-168770_user-iconset-no-profile-picture-icon-circle-clipart.png",i.title,i.vote_average,i.overview))
        }
        topRated = list
        movieListAdapter[2].updateMovies(topRated)
       // notifyDataSetChanged()
    }
    fun updatePopular(movieResponse: List<MovieResult>){
        val list : ArrayList<MovieEntity> = ArrayList()
        for(i in movieResponse){
            if(i.poster_path!=null)
                list.add(MovieEntity(i.poster_path,i.title,i.vote_average,i.overview))
//            else
//                list.add(MovieEntity("https://www.pikpng.com/pngl/m/16-168770_user-iconset-no-profile-picture-icon-circle-clipart.png",i.title,i.vote_average,i.overview))
        }
        popular = list
        movieListAdapter[1].updateMovies(popular)
       // notifyDataSetChanged()
    }
}