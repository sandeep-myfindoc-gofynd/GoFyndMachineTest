package com.gofynd.repositories

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.gofynd.BuildConfig
import com.gofynd.model.topRateMoviesList.ServerResponse
import com.gofynd.model.topRateMoviesList.TopRatedMovie
import com.gofynd.service.ApiService
import com.gofynd.service.NetworkConnection
import com.gofynd.service.RestClient
import retrofit2.Call
import retrofit2.Callback

// It act as a Repositry
class PlayerDataSource : PageKeyedDataSource<Long, TopRatedMovie?>() {
    private var service: ApiService? = null
    private val limit = 20
    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, TopRatedMovie?>) {
        Log.d("Intial Load", "Intial Load")
        service = RestClient(NetworkConnection()).client
        service?.getListOfTopRatedMovies(BuildConfig.apiKey, 1)?.enqueue(object : Callback<ServerResponse?> {
            override fun onResponse(call: Call<ServerResponse?>, serverResponse: retrofit2.Response<ServerResponse?>) {
                Log.d("Inside Success","Inside Success"+serverResponse.body()?.results);
                if (serverResponse != null && serverResponse.body() != null) {
                    val topRatedMovieList: List<TopRatedMovie?>? = serverResponse?.body()?.results
                    callback.onResult(topRatedMovieList!!, null, 2.toLong())
                }
            }

            override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {
                Log.d("Inside Failure",t.message+"");
            }
        })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, TopRatedMovie?>) {
        Log.d("Load Before", "Load Before")
        service = RestClient(NetworkConnection()).client
        Log.d("Param key", params.key.toString() + "")
        service?.getListOfTopRatedMovies(BuildConfig.apiKey,params.key)?.enqueue(object : Callback<ServerResponse?> {
            override fun onResponse(call: Call<ServerResponse?>, serverResponse: retrofit2.Response<ServerResponse?>) {
                if (serverResponse != null && serverResponse.body() != null) {
                    val topRatedMovieList: List<TopRatedMovie?>? = serverResponse?.body()?.results
                    val key: Long
                    key = if (params.key > 1) {
                        params.key - 1
                    } else {
                        0
                    }
                    callback.onResult(topRatedMovieList!!, key)
                }
            }

            override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {}
        })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, TopRatedMovie?>) {
        Log.d("Load After", "Load After")
        service = RestClient(NetworkConnection()).client
        Log.d("Param key", params.key.toString() + "")
        service?.getListOfTopRatedMovies(BuildConfig.apiKey,params.key)?.enqueue(object : Callback<ServerResponse?> {
            override fun onResponse(call: Call<ServerResponse?>, serverResponse: retrofit2.Response<ServerResponse?>) {
                if (serverResponse != null && serverResponse.body() != null) {
                    val topRatedMovieList: List<TopRatedMovie?>? = serverResponse?.body()?.results
                    callback.onResult(topRatedMovieList!!, params.key + 1)
                }
            }

            override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {}
        })
    }
}