package com.gofynd.view

import android.app.Application
import androidx.room.Room
import com.gofynd.broadcastreceiver.ConnectivityReceiver
import com.gofynd.database.WishListDatabase


class GoFyndApplication : Application() {
  //  API-key 2842ea850bcbf706492dfcba37c576d0
    // Access Token - eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyODQyZWE4NTBiY2JmNzA2NDkyZGZjYmEzN2M1NzZkMCIsInN1YiI6IjYwMWJmYTVhOGE4OGIyMDAzY2I3NmJiOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.FRrW8y1PFXUD-vbDS35Ggq_YaYuMpa0UVWhzahFPzcU

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun setConnectivityListener(listener: ConnectivityReceiver.ConnectivityReceiverListener?) {
        ConnectivityReceiver.connectivityReceiverListener = listener
    }

    companion object {
        @get:Synchronized
        var instance: GoFyndApplication? = null
    }
}