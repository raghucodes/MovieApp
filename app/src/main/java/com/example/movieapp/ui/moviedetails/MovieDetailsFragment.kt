package com.example.movieapp.ui.moviedetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.domain.entities.MovieEntity
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class MovieDetailsFragment : Fragment() {
    private lateinit var imageView : ImageView
    private lateinit var title : TextView
    private lateinit var overview : TextView
    private lateinit var rating : TextView
    private lateinit var movie : MovieEntity
    private lateinit var saved : ImageView
    val movieDetailsViewModel : MovieDetailsViewModel by viewModel()
    val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moviedetails, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.image)
        title = view.findViewById(R.id.title)
        overview = view.findViewById(R.id.overview)
        rating = view.findViewById(R.id.rating)
        saved = view.findViewById(R.id.save)

        val bundle : MovieEntity? = this.arguments?.getParcelable<MovieEntity>("movieobject")
        if (bundle != null) {
            movie = bundle
        }

        val url  = movie.url
        Log.e("movie detail url",url)
        if (url != null) {
            Picasso.get().load(BASE_IMAGE_URL+url).into(imageView)
        }
        title.text = movie.movieName
        rating.text = movie.rating.toString()
        overview.text = movie.overview

        movieDetailsViewModel.checkSaved(movie.movieName).observe(viewLifecycleOwner, Observer {
            if(it != null)
                saved.setImageResource(R.drawable.ic_baseline_bookmark_24)

//            Log.e("movie name",it)
        })

        saved.setOnClickListener {
            movieDetailsViewModel.insertSavedMovies(movie)
        }
    }

}