package com.swap.spacex.features.feed.data

import androidx.paging.Pager
import com.swap.spacex.features.feed.model.PastLaunchesResponse
import com.swap.spacex.utils.ResourceProvider
import javax.inject.Inject

class FeedService @Inject constructor(
    private val repository: FeedRepository
) {
    internal fun getPastLaunches(resourceProvider: ResourceProvider): Pager<Int, PastLaunchesResponse> {
        return repository.getAllPastLaunches(resourceProvider)
    }
}