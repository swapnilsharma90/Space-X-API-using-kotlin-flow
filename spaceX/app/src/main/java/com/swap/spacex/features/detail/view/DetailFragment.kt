package com.swap.spacex.features.detail.view

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.swap.spacex.MainActivity
import com.swap.spacex.R
import com.swap.spacex.base.BaseFragment
import com.swap.spacex.databinding.FragmentDetailBinding
import com.swap.spacex.di.component.InstanceComponent
import com.swap.spacex.utils.getDateFromTimestamp

class DetailFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun inject(injector: InstanceComponent) {
        injector.inject(this)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity?)?.supportActionBar?.title = getString(R.string.launch_details)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        if (arguments != null) {
            val pastLaunchesResponse = DetailFragmentArgs.fromBundle(requireArguments()).launch
            binding.missionName.text = getString(R.string.mission_name) + pastLaunchesResponse.mission_name
            binding.launchDate.text = getString(R.string.launch_date) + pastLaunchesResponse.launch_date_utc?.let { getDateFromTimestamp(it) }

            if (pastLaunchesResponse.launch_success) binding.missionStatus.text = getString(R.string.status) + getString(R.string.launch_success)
            else binding.missionStatus.text = getString(R.string.status) + getString(R.string.launch_failed)

            if (!pastLaunchesResponse.details.isNullOrEmpty())
            {
                binding.missionDetail.text = getString(R.string.details) + pastLaunchesResponse.details
            }
            else
            {
                binding.missionDetail.visibility=View.GONE
            }

            binding.btnWatchVideo.setOnClickListener{
                val intentApp = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("vnd.youtube:" + pastLaunchesResponse.links?.youtube_id)
                )
                val intentBrowser = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + pastLaunchesResponse.links?.youtube_id)
                )
                try {
                    this.startActivity(intentApp)
                } catch (ex: ActivityNotFoundException) {
                    this.startActivity(intentBrowser)
                }
            }
        }
        return binding.root
    }
}