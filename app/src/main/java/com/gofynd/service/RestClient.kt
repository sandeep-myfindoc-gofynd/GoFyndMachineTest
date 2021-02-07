package com.gofynd.service

import android.util.Log
import com.gofynd.util.CommonUtility
import com.gofynd.view.GoFyndApplication.Companion.instance
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Rest client
 */
class RestClient(private val networkConnection: NetworkConnection) {
    // setting regarding connection and handle failure cases
    // used to conver json/xml to pojo class.
    val client: ApiService
        get() {
            if (retrofit == null) {
                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                retrofit = Retrofit.Builder()
                    .baseUrl(networkConnection.baseUrl)
                    .client(okHttpClient) // setting regarding connection and handle failure cases
                    .addConverterFactory(GsonConverterFactory.create(gson)) // used to conver json/xml to pojo class.
                    .build()
            }
            return retrofit!!.create(ApiService::class.java)
        }

    private val okHttpClient: OkHttpClient
        private get() {
            var httpClient: OkHttpClient.Builder? = null
            try {
                httpClient = OkHttpClient.Builder()
                    .hostnameVerifier { s, sslSession -> true }
                    .cache(provideCache())
                    .callTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(offLineInterceptor())
                    .addNetworkInterceptor(onLineInterceptor())
                return httpClient.build()
            } catch (e: Exception) {
                RuntimeException()
            }
            return httpClient!!.build()
        }

    private fun provideCache(): Cache? {
        var cache: Cache? = null
        try {
            val cacheSize = 10 * 1024 * 1024
            cache = Cache(
                File(
                    instance!!.cacheDir,
                    "http-cache"
                ), cacheSize.toLong()
            )
        } catch (ex: Exception) {
            Log.d("error creating cache", ex.message + "")
        }
        return cache
    }

    private fun onLineInterceptor(): Interceptor {
        return Interceptor { chain ->
            Log.d("TAG", "network interceptor: called.")
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(5, TimeUnit.SECONDS)
                .build()
            response.newBuilder()
                .header("Cache-Control", "public, max-age=" + 60)
                .removeHeader("Pragma")
                .build()
        }
    }

    private fun offLineInterceptor(): Interceptor {
        return Interceptor { chain ->
            Log.d("TAG", "offline interceptor: called.")
            var request = chain.request()
            // prevent caching when network is on. For that we use the "networkInterceptor"
            if (!CommonUtility(instance!!).checkInternetConnection()) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
                Log.d("cacheControl", cacheControl.toString())
                request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + 60)
                    .removeHeader("Pragma")
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }

    companion object {
        var retrofit: Retrofit? = null
    }

}