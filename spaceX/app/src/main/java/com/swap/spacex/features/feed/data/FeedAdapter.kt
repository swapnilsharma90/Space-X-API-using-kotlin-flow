package com.swap.spacex.features.feed.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.swap.spacex.databinding.ViewSingleLaunchBinding
import com.swap.spacex.features.feed.model.PastLaunchesResponse
import com.swap.spacex.utils.getDateFromTimestamp


class FeedAdapter(private val clickListener: (response: PastLaunchesResponse) -> Unit) :
    PagingDataAdapter<PastLaunchesResponse, FeedAdapter.FeedViewHolder>(FeedComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedViewHolder {
        return FeedViewHolder(
            ViewSingleLaunchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindLaunchData(it, clickListener) }
    }

    inner class FeedViewHolder(private val binding: ViewSingleLaunchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindLaunchData(
            itemData: PastLaunchesResponse,
            clickListener: (PastLaunchesResponse) -> Unit
        ) = with(binding) {
            missionName.text = itemData.mission_name
            launchDate.text = itemData.launch_date_utc?.let { getDateFromTimestamp(it) }
            binding.root.setOnClickListener { clickListener(itemData) }
        }
    }


    object FeedComparator : DiffUtil.ItemCallback<PastLaunchesResponse>() {
        override fun areItemsTheSame(
            oldItem: PastLaunchesResponse,
            newItem: PastLaunchesResponse
        ): Boolean {
            return oldItem.mission_id == newItem.mission_id
        }

        override fun areContentsTheSame(
            oldItem: PastLaunchesResponse,
            newItem: PastLaunchesResponse
        ): Boolean {
            return oldItem == newItem
        }
    }
}