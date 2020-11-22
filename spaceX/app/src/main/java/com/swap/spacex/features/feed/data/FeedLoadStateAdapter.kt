package com.swap.spacex.features.feed.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swap.spacex.databinding.ItemLoadingStateBinding
import com.swap.spacex.utils.visible

class FeedLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<FeedLoadStateAdapter.FeedLoadStateViewHolder>() {

    inner class FeedLoadStateViewHolder(
        private val binding: ItemLoadingStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.tvError.text = loadState.error.localizedMessage
            }
            binding.progressbar.visible(loadState is LoadState.Loading)
            binding.btnRetry.visible(loadState is LoadState.Error)
            binding.tvError.visible(loadState is LoadState.Error)
            binding.btnRetry.setOnClickListener {
                retry()
            }
        }
    }

    override fun onBindViewHolder(holder: FeedLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = FeedLoadStateViewHolder(
        ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retry
    )
}