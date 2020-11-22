package com.swap.spacex

import android.app.Application
import com.swap.spacex.di.component.ApplicationComponent
import com.swap.spacex.di.component.DaggerApplicationComponent


class SpaceXApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().apply {
            context(this@SpaceXApplication)
        }.build()
    }

}