package com.gofynd.service

import com.gofynd.model.topRateMoviesList.ServerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/v1/players")
    fun getListOfPlayers(@Query("page") page: Long, @Query("per_page") limit: Int): Call<ServerResponse?>

    @GET("movie/top_rated")
    fun getListOfTopRatedMovies(@Query("api_key") apiKey: String, @Query("page") page: Long): Call<ServerResponse?>
}