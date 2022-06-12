package com.rizalfirman.co_ffee

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)


        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, IntroActivity::class.java))
        }, 3000L)
    }
}