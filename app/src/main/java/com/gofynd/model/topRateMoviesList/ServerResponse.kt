package com.gofynd.model.topRateMoviesList

import com.google.gson.annotations.SerializedName
import java.util.*

data class ServerResponse constructor(@SerializedName("page") val page : Int,
                                      @SerializedName("results") val results : List<TopRatedMovie>,
@SerializedName("total_pages") val total_pages : Int,
@SerializedName("total_results") val total_results : Int)
{


}