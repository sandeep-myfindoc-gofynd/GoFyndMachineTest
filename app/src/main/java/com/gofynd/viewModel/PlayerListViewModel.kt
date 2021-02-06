package com.gofynd.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gofynd.model.topRateMoviesList.TopRatedMovie
import com.gofynd.repositories.PlayerDataSourceFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
//JsonObject
class PlayerListViewModel(application: Application) : AndroidViewModel(application) {
    var topRatedMoviePagedList: LiveData<PagedList<TopRatedMovie>>
    private val executor: Executor

    init {
        val factory = PlayerDataSourceFactory()
        //liveDataSource = factory.getMutableLiveData();
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build()
        executor = Executors.newFixedThreadPool(5)
        topRatedMoviePagedList = LivePagedListBuilder(factory, config)
                .setFetchExecutor(executor)
                .build()
    }
}