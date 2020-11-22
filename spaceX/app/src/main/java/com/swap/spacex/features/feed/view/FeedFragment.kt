package com.swap.spacex.features.feed.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.swap.spacex.MainActivity
import com.swap.spacex.R
import com.swap.spacex.SpaceXApplication
import com.swap.spacex.base.BaseCoordinator
import com.swap.spacex.base.BaseFragment
import com.swap.spacex.databinding.FragmentFeedBinding
import com.swap.spacex.di.component.InstanceComponent
import com.swap.spacex.features.feed.data.FeedAdapter
import com.swap.spacex.features.feed.data.FeedLoadStateAdapter
import com.swap.spacex.features.feed.data.FeedViewModel
import com.swap.spacex.features.feed.data.FeedViewState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedFragment : BaseFragment() {
    @Inject
    lateinit var viewModel: FeedViewModel
    private lateinit var binding: FragmentFeedBinding

    override fun inject(injector: InstanceComponent) {
        injector.inject(this)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity?)?.supportActionBar?.title = "Past launches"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.coordinator =
            BaseCoordinator(requireActivity().application as SpaceXApplication, findNavController())

        viewModel.viewState.observe(viewLifecycleOwner, {
            it?.let(::updateUI)
        })


        val adapter = FeedAdapter {
            val bundle = Bundle()
            bundle.putParcelable("launch", it)
            viewModel.coordinator.navigateTo(
                R.id.detailFragment,
                bundle
            )

        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = FeedLoadStateAdapter { adapter.retry() },
            footer = FeedLoadStateAdapter { adapter.retry() }
        )

        lifecycleScope.launch {
            viewModel.getFeeds().collectLatest { pagedData ->
                adapter.submitData(pagedData)
            }
        }

        return binding.root
    }

    private fun updateUI(state: FeedViewState) {
        when (state) {

            is FeedViewState.DataLoaded -> {
                //toggle progressbar visibility

            }
            is FeedViewState.Error -> {
//                toggle progressbar visibility
//                show error

            }

            is FeedViewState.Loading -> {
                //toggle progressbar visibility
            }
        }
    }
}