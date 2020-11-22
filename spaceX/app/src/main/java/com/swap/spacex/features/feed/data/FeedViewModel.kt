package com.swap.spacex.features.feed.data

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.swap.spacex.base.BaseViewModel
import com.swap.spacex.features.feed.model.PastLaunchesResponse
import com.swap.spacex.utils.ResourceProvider
import com.swap.spacex.utils.SingleLiveEvent
import retrofit2.HttpException

sealed class FeedViewState {
    object Loading : FeedViewState()
    object DataLoaded : FeedViewState()
    data class Error(val error: HttpException) : FeedViewState()

}

class FeedViewModel(
    private val service: FeedService,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {
    var viewState = SingleLiveEvent<FeedViewState>()

    init {
        viewState.value = FeedViewState.Loading
    }


    fun getFeeds(): kotlinx.coroutines.flow.Flow<PagingData<PastLaunchesResponse>> {
        return service.getPastLaunches(resourceProvider).flow.cachedIn(viewModelScope)
    }
//     fun getAllData() {
//        compositeDisposable += service.getPastLaunches()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                viewState.value = it
//            }
//    }

}