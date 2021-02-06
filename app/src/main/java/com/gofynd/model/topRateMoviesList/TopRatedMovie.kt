package com.gofynd.model.topRateMoviesList

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gofynd.R
import com.google.gson.annotations.SerializedName


data class TopRatedMovie constructor(@SerializedName("adult") val adult : Boolean,
                                     @SerializedName("backdrop_path") val backdrop_path : String,
                                     @SerializedName("genre_ids") val genre_ids : List<Int>,
                                     @SerializedName("id") val id : Int,
                                     @SerializedName("original_language") val original_language : String,
                                     @SerializedName("original_title") val original_title : String,
                                     @SerializedName("overview") val overview : String,
                                     @SerializedName("popularity") val popularity : Double,
                                     @SerializedName("poster_path") var poster_path : String,
                                     @SerializedName("release_date") val release_date : String,
                                     @SerializedName("title") val title : String,
                                     @SerializedName("video") val video : Boolean,
                                     @SerializedName("vote_average") val vote_average : String,
                                     @SerializedName("vote_count") val vote_count : Int, var isAddedToWishList : Boolean)
{
    companion object{
        @JvmStatic @BindingAdapter("poster_path")
        fun loadImage(imageView: ImageView, imageURL: String?) {
            var basePath : String;
            basePath = "https://image.tmdb.org/t/p/w500/";
            Glide.with(imageView.context)
                .load(basePath+imageURL)
                .placeholder(R.drawable.loading)
                .into(imageView)
        }
    }


}