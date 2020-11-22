package com.swap.spacex.features.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.swap.spacex.MainActivity
import com.swap.spacex.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        //5second splash time
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)

            finish()
        },5000)
    }
}