package com.swap.spacex.di.module


import androidx.lifecycle.ViewModelProvider
import com.swap.spacex.di.component.ViewModelHost
import com.swap.spacex.di.scope.PerInstance
import com.swap.spacex.features.feed.data.FeedViewModel
import com.swap.spacex.utils.ResourceProvider
import dagger.Module
import dagger.Provides

@Module
object ViewModelModule {
    @Provides
    @PerInstance
    @JvmStatic
    internal fun provideViewModelProvider(host: ViewModelHost, factory: ViewModelFactory) = when (host) {
        is ViewModelHost.ActivityHost -> ViewModelProvider(host.activity, factory)
        is ViewModelHost.FragmentHost -> ViewModelProvider(host.fragment, factory)
    }

    @Provides
    @PerInstance
    @JvmStatic
    internal fun provideResourceProvider(host: ViewModelHost) = when (host) {
        is ViewModelHost.ActivityHost -> ResourceProvider(host.activity)
        is ViewModelHost.FragmentHost -> ResourceProvider(host.fragment.requireContext())
    }

    @Provides
    @PerInstance
    @JvmStatic
    internal fun provideFeedViewModel(viewModelProvider: ViewModelProvider) =
        viewModelProvider.get(FeedViewModel::class.java)
}