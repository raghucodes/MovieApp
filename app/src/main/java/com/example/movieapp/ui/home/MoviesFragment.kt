package com.example.movieapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.domain.entities.MovieEntity
import com.example.movieapp.domain.entities.SectionEntity
import com.example.movieapp.ui.MainActivity
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MoviesFragment : Fragment() {

    private lateinit var sectionRecyclerView: RecyclerView
    private lateinit var sectionListAdapter: SectionListAdapter
    private val moviesViewModel : MoviesViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nowPlayingList : ArrayList<MovieEntity> = ArrayList()
        val popularList : ArrayList<MovieEntity> = ArrayList()
        val topRatedList : ArrayList<MovieEntity> = ArrayList()
       // val movies : ArrayList<MovieResponse> = ArrayList()

        nowPlayingList.add(MovieEntity("https://www.pikpng.com/pngl/m/16-168770_user-iconset-no-profile-picture-icon-circle-clipart.png","sample",5.0,"hello"))
        popularList.add(MovieEntity("https://www.pikpng.com/pngl/m/16-168770_user-iconset-no-profile-picture-icon-circle-clipart.png","sample",5.0,"hello"))
        topRatedList.add(MovieEntity("https://www.pikpng.com/pngl/m/16-168770_user-iconset-no-profile-picture-icon-circle-clipart.png","sample",5.0,"hello"))

        val nowPlayingSectionEntity = SectionEntity("Now Playing",nowPlayingList)
        val popularSectionEntity = SectionEntity("Popular",popularList)
        val topRatedSectionEntity = SectionEntity("Top Rated",topRatedList)

        val moviesSection : ArrayList<SectionEntity> = ArrayList()
        moviesSection.add(nowPlayingSectionEntity)
        moviesSection.add(popularSectionEntity)
        moviesSection.add(topRatedSectionEntity)

       // shimmer = view.findViewById<ShimmerFrameLayout>(R.id.shimmer)
        sectionListAdapter = SectionListAdapter(moviesSection)
        sectionRecyclerView = view.findViewById(R.id.Section_List)
        sectionRecyclerView.adapter = sectionListAdapter
        sectionRecyclerView.layoutManager = LinearLayoutManager(activity)
       // sectionRecyclerView.itemAnimator = DefaultItemAnimator()
        moviesViewModel.getNowPlayingMovies().observe(context as MainActivity, Observer {
            Log.e("result",it.results.toString())
            sectionListAdapter.updateNowPlaying(it.results)
        })

        moviesViewModel.getTopRatedMovies().observe(context as MainActivity, Observer {
            sectionListAdapter.updateTopRated(it.results)
        })

        moviesViewModel.getPopularMovies().observe(context as MainActivity, Observer {
            sectionListAdapter.updatePopular(it.results)
        })

    }
}
