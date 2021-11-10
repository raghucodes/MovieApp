package com.example.movieapp.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import org.koin.android.ext.android.inject

class SearchFragment : Fragment() {
    private lateinit var searchRecyclerView: RecyclerView
    private lateinit var    searchListAdapter :  SearchListAdapter
    private val searchMoviesViewModel: SearchViewModel by inject()
    private lateinit var searchString : EditText
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchString = view.findViewById(R.id.search_text)

        searchListAdapter = SearchListAdapter()
        searchRecyclerView = view.findViewById(R.id.search_list)
        searchRecyclerView.adapter = searchListAdapter
        searchRecyclerView.layoutManager = LinearLayoutManager(activity)
       // searchRecyclerView.itemAnimator = DefaultItemAnimator()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchString.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchMoviesViewModel.searchMovies(s.toString()).observe(viewLifecycleOwner,
                    Observer {
                        searchListAdapter.updateList(it.results)
                    })
            }

        })
    }

}