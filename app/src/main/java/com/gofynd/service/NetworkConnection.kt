package com.gofynd.service

import com.gofynd.BuildConfig


class NetworkConnection {
    var baseUrl: String? = null

    init {
        if (BuildConfig.DEBUG) {
            baseUrl = BuildConfig.baseUrl
        } else {
            baseUrl = BuildConfig.baseUrl
        }
    }
}