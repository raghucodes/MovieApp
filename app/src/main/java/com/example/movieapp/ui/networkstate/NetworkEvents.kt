package com.example.movieapp.ui.networkstate

import androidx.lifecycle.LiveData

object NetworkEvents : LiveData<Event>() {
    internal fun notify(event: Event) {
        postValue(event)
    }
}