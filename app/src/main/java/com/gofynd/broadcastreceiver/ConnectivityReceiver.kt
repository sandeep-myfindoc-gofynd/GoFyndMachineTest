package com.gofynd.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.gofynd.util.CommonUtility


class ConnectivityReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val isConnected = CommonUtility(context).checkInternetConnection()
        if (connectivityReceiverListener != null) connectivityReceiverListener!!.onNetworkConnectionChanged(isConnected)
    }

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {
        var connectivityReceiverListener: ConnectivityReceiverListener? = null
    }
}