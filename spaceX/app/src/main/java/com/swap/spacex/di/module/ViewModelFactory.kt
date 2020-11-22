package com.swap.spacex.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swap.spacex.di.scope.PerInstance
import com.swap.spacex.features.feed.data.FeedService
import com.swap.spacex.features.feed.data.FeedViewModel
import com.swap.spacex.utils.ResourceProvider

import javax.inject.Inject

@PerInstance
class ViewModelFactory @Inject constructor(
    private val feedService: FeedService,
    private val resourceProvider: ResourceProvider
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {

            FeedViewModel::class.java -> modelClass.getConstructor(
                FeedService::class.java,
                ResourceProvider::class.java
            ).newInstance(feedService, resourceProvider)


//            SomeOtherVM::class.java -> modelClass.getConstructor(
//                FeedService::class.java,
//                ResourceProvider::class.java
//            ).newInstance(feedService,resourceProvider)

            else -> TODO("Missing viewModel $modelClass")
        } as T
    }
}