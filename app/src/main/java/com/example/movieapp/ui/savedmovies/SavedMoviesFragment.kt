package com.example.movieapp.ui.savedmovies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import org.koin.android.ext.android.inject


class SavedMoviesFragment : Fragment() {
    private lateinit var savedMoviesrRecyclerView: RecyclerView
    private lateinit var savedMoviesListAdapter : SavedMovieListAdapter
    private val savedMoviesViewModel : SavedMoviesViewModel by inject()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedMoviesrRecyclerView = view.findViewById(R.id.saved_movies_list)
        savedMoviesListAdapter = SavedMovieListAdapter(requireContext(),savedMoviesViewModel)
        savedMoviesrRecyclerView.adapter = savedMoviesListAdapter
        savedMoviesrRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        savedMoviesrRecyclerView.itemAnimator = DefaultItemAnimator()

        Log.e("tag","in saved fragment")

        savedMoviesViewModel.getSavedMovies().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            savedMoviesListAdapter.updateJokeList(it)
        })
    }

}