package com.swap.spacex.di.component

import android.content.Context
import com.swap.spacex.SpaceXApplication
import com.swap.spacex.api.SpaceXApi
import com.swap.spacex.di.module.NetworkModule
import com.swap.spacex.di.module.UtilModule
import com.swap.spacex.features.feed.data.FeedService


import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, UtilModule::class])
interface ApplicationComponent {
    fun inject(application: SpaceXApplication)
    fun provideSpaceXApi(): SpaceXApi
    fun provideFeedService(): FeedService

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): ApplicationComponent
    }
}