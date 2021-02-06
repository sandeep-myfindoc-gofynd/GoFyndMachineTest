package com.gofynd.util

import android.content.Context
import android.net.ConnectivityManager

class CommonUtility(private val mContext: Context) {
    fun checkInternetConnection(): Boolean {
        return try {
            val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
        } catch (ex: Exception) {
            false
        }
    }

}