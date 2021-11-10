package com.example.movieapp.ui.networkstate

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities

internal class NetworkStateImp : NetworkState {
    override var network: Network? = null

    override var isConnected: Boolean = false
        set(value) {
            field = value
            NetworkEvents.notify(if (value) Event.ConnectivityAvailable else Event.ConnectivityLost)
        }
    override var linkProperties: LinkProperties? = null
        set(value) {
            val event = Event.LinkPropertyChanged(field)
            field = value
            NetworkEvents.notify(event)
        }

    override var networkCapabilities: NetworkCapabilities? = null
        set(value) {
            val event = Event.NetworkCapabilityChanged(field)
            field = value
            NetworkEvents.notify(event)
        }
}