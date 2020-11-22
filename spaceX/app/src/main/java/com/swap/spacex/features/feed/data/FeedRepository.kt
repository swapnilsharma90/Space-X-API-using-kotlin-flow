package com.swap.spacex.features.feed.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.swap.spacex.api.SpaceXApi
import com.swap.spacex.features.feed.model.PastLaunchesResponse
import com.swap.spacex.utils.ErrorHandlingUtils
import com.swap.spacex.utils.ResourceProvider
import javax.inject.Inject

class FeedRepository @Inject constructor(private var api: SpaceXApi) {

    fun getAllPastLaunches(resourceProvider: ResourceProvider): Pager<Int, PastLaunchesResponse> {
        return Pager(PagingConfig(pageSize = 10)) {
            FeedDataSource(api, resourceProvider)
        }
    }

    internal class FeedDataSource(
        private val api: SpaceXApi,
        private val resourceProvider: ResourceProvider
    ) : PagingSource<Int, PastLaunchesResponse>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PastLaunchesResponse> {
            return try {
                val nextPageNumber = params.key ?: 0
                val response = api.getAllPastLaunches(nextPageNumber)
                LoadResult.Page(
                    data = response,
                    prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                    nextKey = if (nextPageNumber < response.size) nextPageNumber + 1 else null
                )

            } catch (e: Exception) {
                var errorHandlingUtils = ErrorHandlingUtils(resourceProvider)
                // use errorHandlingUtils for API errors
                LoadResult.Error(e)
            }
        }
    }
}