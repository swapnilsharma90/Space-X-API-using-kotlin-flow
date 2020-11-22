package com.swap.spacex.base

import android.app.Application
import android.os.Bundle
import androidx.navigation.AnimBuilder
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.swap.spacex.R
import javax.inject.Inject

open class BaseCoordinator @Inject constructor (
    private val app: Application,
   private val navigator: NavController

) {
    private val animBuilder: AnimBuilder.() -> Unit =
        {
            this.enter = R.anim.enter_anim
            this.exit = R.anim.exit_anim
            this.popEnter = R.anim.pop_enter_anim
            this.popExit = R.anim.pop_exit_anim
        }

    fun navigateTo(
        target: Int, bundle: Bundle? = null,
        navOptions: (NavOptionsBuilder.() -> Unit)? = null
    ) {
        navigator.run {
            navigate(target, bundle, androidx.navigation.navOptions {
                anim(animBuilder)
                navOptions?.let { this.it() }
            })
        }
    }

    //other methods can be added as project grows

}