package com.example.movieapp.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.domain.di.savedMoviesApplicationContext
import com.example.movieapp.ui.home.MoviesFragment
import com.example.movieapp.ui.networkstate.Event
import com.example.movieapp.ui.networkstate.NetworkEvents
import com.example.movieapp.ui.savedmovies.SavedMoviesFragment
import com.example.movieapp.ui.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

lateinit var context: Context
class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        savedMoviesApplicationContext = applicationContext

        val navigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigationView.setOnNavigationItemSelectedListener(navigationListener)
        openAlbumFragment()
        NetworkEvents.observe(this, Observer {
            if(it is Event.ConnectivityAvailable){
                Toast.makeText(applicationContext,"connected",Toast.LENGTH_SHORT).show()
                // dialog?.hide()
            }
            if(it is Event.ConnectivityLost){
                Toast.makeText(applicationContext,"disconnected",Toast.LENGTH_LONG).show()
                displayDialog()
            }
        })
    }

    fun displayDialog(){
        val builder: AlertDialog.Builder? = this?.let { AlertDialog.Builder(it) }
        builder?.setMessage("No Internet Detected")?.setTitle("Connection Lost")
        builder?.apply {
            setPositiveButton("open settings", DialogInterface.OnClickListener{ dialog, id ->
                val panelIntent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
                startActivityForResult(panelIntent, 545)
            })
            setNegativeButton("cancel", DialogInterface.OnClickListener{ dialog, id ->

            })
        }
        val dialog: AlertDialog? = builder?.create()
        dialog?.show()
    }

    private fun openAlbumFragment(){
        val fragment : Fragment = MoviesFragment()
        loadFragment(fragment)
    }

    private fun loadFragment(fragment: Fragment){
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private val navigationListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {

                R.id.menu_home -> {
                    val fragment: Fragment = MoviesFragment()
                    loadFragment(fragment)
                    return true
                }

                R.id.menu_search -> {
                    val fragment: Fragment = SearchFragment()
                    loadFragment(fragment)
                    return true
                }

                R.id.menu_saved ->{
                    val fragment : Fragment = SavedMoviesFragment()
                    loadFragment(fragment)
                    return true
                }
            }
            return false
        }
    }
}