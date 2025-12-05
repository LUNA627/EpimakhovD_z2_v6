package com.example.prack_2_epimakhovd.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import com.example.prack_2_epimakhovd.R
import android.os.Handler


class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, SignInScreenActivity::class.java))
            finish()
        }, 1000)

    }
}
