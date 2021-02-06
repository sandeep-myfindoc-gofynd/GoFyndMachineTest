package com.gofynd.view.TopRatedMovieDetail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.gofynd.R
import com.gofynd.databinding.ActivityPlayerDetailBinding
import com.gofynd.model.topRateMoviesList.TopRatedMovie
import com.gofynd.sharedpreferences.SharedPreferencesName
import com.gofynd.view.SuperActivity

class TopRatedMovieDetail : SuperActivity() {
    private var topRatedMovie: TopRatedMovie? = null
    private var binding: ActivityPlayerDetailBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player_detail)
        updateToolbar(resources.getString(R.string.title_movie_detail))
        if (intent != null) {
            val topRatedMovieString = intent.getStringExtra(SharedPreferencesName.TOP_RATED_MOVIE_DETAIL)
            topRatedMovie = Gson().fromJson(topRatedMovieString, TopRatedMovie::class.java)
            binding?.movieDetail = topRatedMovie
        }
    }

    private fun updateUI() {
       /* if (topRatedMovie != null) {
            binding!!.labelNameValue.text = topRatedMovie!!.firstName + " " + topRatedMovie!!.lastName
            binding!!.labelPositionValue.text = if (topRatedMovie!!.position != null && topRatedMovie!!.position != "") topRatedMovie!!.position else emptyLabel
            binding!!.labelheightValue.text = (if (topRatedMovie!!.heightFeet != null && topRatedMovie!!.heightFeet != "") topRatedMovie!!.heightFeet else emptyLabel) + " Feet " + ((if (topRatedMovie!!.heightInches != null) topRatedMovie!!.heightInches else emptyLabel) + " Inches")
            binding!!.labelweightValue.text = (if (topRatedMovie!!.weightPounds != null) topRatedMovie!!.weightPounds else emptyLabel) + " Pounds"
            binding!!.labelTeamValue.text = if (topRatedMovie!!.team!!.fullName != null) topRatedMovie!!.team!!.fullName else emptyLabel
            binding!!.labelCityValue.text = if (topRatedMovie!!.team!!.city != null) topRatedMovie!!.team!!.city else emptyLabel
            binding!!.labelConfrenceValue.text = if (topRatedMovie!!.team!!.conference != null) topRatedMovie!!.team!!.conference else emptyLabel
            binding!!.labelDivisionValue.text = if (topRatedMovie!!.team!!.division != null) topRatedMovie!!.team!!.division else emptyLabel
        }*/
    }
}