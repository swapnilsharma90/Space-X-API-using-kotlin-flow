package com.swap.spacex.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.swap.spacex.SpaceXApplication
import com.swap.spacex.di.component.DaggerInstanceComponent
import com.swap.spacex.di.component.InstanceComponent
import com.swap.spacex.di.component.ViewModelHost
import com.swap.spacex.di.module.ViewModelModule


abstract class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)

        val injector = DaggerInstanceComponent.builder().apply {
            applicationComponent((requireContext().applicationContext as SpaceXApplication).applicationComponent)
            viewModelModule(ViewModelModule)
            host(ViewModelHost.FragmentHost(this@BaseFragment))
        }.build()
        inject(injector)
    }

    abstract fun inject(injector: InstanceComponent)

}