package com.swap.spacex.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.swap.spacex.SpaceXApplication
import com.swap.spacex.di.component.DaggerInstanceComponent
import com.swap.spacex.di.component.InstanceComponent
import com.swap.spacex.di.component.ViewModelHost
import com.swap.spacex.di.module.ViewModelModule


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val injector = DaggerInstanceComponent.builder().apply {
            applicationComponent((applicationContext as SpaceXApplication).applicationComponent)
            viewModelModule(ViewModelModule)
            host(ViewModelHost.ActivityHost(this@BaseActivity))
        }.build()
        inject(injector)
    }

    abstract fun inject(injector: InstanceComponent)
}
