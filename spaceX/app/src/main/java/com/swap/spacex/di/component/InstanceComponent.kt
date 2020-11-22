package com.swap.spacex.di.component

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.swap.spacex.MainActivity
import com.swap.spacex.di.module.ViewModelModule

import com.swap.spacex.di.scope.PerInstance
import com.swap.spacex.features.detail.view.DetailFragment
import com.swap.spacex.features.feed.view.FeedFragment
import dagger.BindsInstance
import dagger.Component

@PerInstance
@Component(dependencies = [ApplicationComponent::class], modules = [ViewModelModule::class])
interface InstanceComponent {
    fun inject(fragment: FeedFragment)
    fun inject(fragment: DetailFragment)
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        fun applicationComponent(applicationComponent: ApplicationComponent): Builder

        fun viewModelModule(viewModelModule: ViewModelModule): Builder

        @BindsInstance
        fun host(host: ViewModelHost): Builder

        fun build(): InstanceComponent
    }
}

sealed class ViewModelHost {
    data class ActivityHost(val activity: FragmentActivity) : ViewModelHost()
    data class FragmentHost(val fragment: Fragment) : ViewModelHost()
}