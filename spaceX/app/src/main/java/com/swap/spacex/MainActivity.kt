package com.swap.spacex

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.swap.spacex.base.BaseActivity
import com.swap.spacex.di.component.InstanceComponent

class MainActivity : BaseActivity() {
    private var navController: NavController? = null
    private var host: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        host =
            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment?
                ?: return
        navController = host?.navController

        setUpToolbar()
    }

    private fun setUpToolbar() {
        supportActionBar?.title = getString(R.string.past_launches)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun showNoInternetSnackBar() {
    }

    override fun inject(injector: InstanceComponent) {
        injector.inject(this)
    }
}
