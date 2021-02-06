package com.gofynd.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gofynd.model.topRateMoviesList.TopRatedMovie


class PlayerDataSourceFactory : DataSource.Factory<Long, TopRatedMovie>() {
    var playerLiveDataSource: MutableLiveData<PlayerDataSource>
    /*override fun create(): PlayerDataSource {
        val playerDataSource = PlayerDataSource()
        playerLiveDataSource.postValue(playerDataSource)
        return playerDataSource
        //return mutableLiveData
    }*/

    init {
        playerLiveDataSource = MutableLiveData()
    }

    override fun create(): DataSource<Long, TopRatedMovie?> {
        var playerDataSource = PlayerDataSource()
        playerLiveDataSource.postValue(playerDataSource)
        return playerDataSource
    }
}