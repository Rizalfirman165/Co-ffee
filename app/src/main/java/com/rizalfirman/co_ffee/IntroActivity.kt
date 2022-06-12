package com.rizalfirman.co_ffee

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.rizalfirman.co_ffee.databinding.ActivityIntroBinding
import com.rizalfirman.co_ffee.ui.main.MainActivity

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntroBinding.inflate(layoutInflater)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        binding.get.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.about.setOnClickListener {
            intent = Intent(this, AbouteActivity::class.java)
            startActivity(intent)
        }
    }
}