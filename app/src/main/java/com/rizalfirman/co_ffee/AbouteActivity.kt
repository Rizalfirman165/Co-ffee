package com.rizalfirman.co_ffee

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rizalfirman.co_ffee.databinding.ActivityAbouteBinding

class AbouteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAbouteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbouteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}