package com.example.movieapp

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.movieapp.data.database.RoomMovieDatabase
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.data.retrofit.RetrofitInstance.Companion.getRetrofitInstance
import com.example.movieapp.data.retrofit.RetrofitInstance.Companion.httpClient
import com.example.movieapp.data.retrofit.RetrofitInstance.Companion.provideMovieApi
import com.example.movieapp.domain.usecases.*
import com.example.movieapp.ui.home.MoviesViewModel
import com.example.movieapp.ui.moviedetails.MovieDetailsViewModel
import com.example.movieapp.ui.networkstate.NetworkStateHolder.registerConnectivityMonitor
import com.example.movieapp.ui.savedmovies.SavedMoviesViewModel
import com.example.movieapp.ui.search.SearchViewModel
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application(){
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        registerConnectivityMonitor()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule))
        }
    }
}

val appModule = module {

    factory { httpClient() }

    factory { getRetrofitInstance(get()) }

    single { provideMovieApi(get()) }

    single { RoomMovieDatabase.getDatabase(get()).movieDao() }

    factory { MovieRepository(get(),get()) }

    factory { GetNowPlayingMoviesUseCase(get()) }

    factory { GetPopularMoviesUseCase(get()) }

    factory { GetTopRatedMoviesUseCase(get()) }

    factory { GetMoviesUseCase(get(),get(),get()) }

    factory { InsertSavedMovieUseCase(get()) }

    factory { DeleteSavedMovieUseCase(get()) }

    factory { GetSavedMoviesUseCase(get()) }

    factory { SearchMoviesUseCase(get()) }

    factory { CheckSavedUseCase(get()) }

    viewModel { MoviesViewModel(get()) }

    viewModel { SearchViewModel(get()) }

    viewModel { SavedMoviesViewModel(get(),get()) }

    viewModel { MovieDetailsViewModel(get(),get()) }

}