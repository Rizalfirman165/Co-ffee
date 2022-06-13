package com.rizalfirman.co_ffee.ui.result

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rizalfirman.co_ffee.config.response.ResponseMoisture
import com.rizalfirman.co_ffee.databinding.ActivityResultMoistureBinding
import com.rizalfirman.co_ffee.ui.main.MainActivity

class ResultMoistureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultMoistureBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultMoistureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultMoisture = intent.getParcelableExtra<ResponseMoisture>(ResultMoistureActivity.DATA_MOISTURE)
        Log.d("ss ", resultMoisture.toString())


        val resultImage = intent.getStringExtra(ResultMoistureActivity.EXTRA_IMAGE)
//        val drawable : Drawable = resources.getDrawable(resultImage)
        Log.d("ss ", resultMoisture.toString())
        binding.previewImageView.setImageURI(Uri.parse(resultImage))
        Log.d("munculgeas ", resultImage.toString())
        binding.apply {
            tvResult.text = "Moisture Level tester \n${resultMoisture?.kadarAir}"
        }


        binding.iconBack.setOnClickListener {
            val movIntent = Intent(this, MainActivity::class.java)
            startActivity(movIntent)
            finish()
        }
    }

    companion object {
        const val DATA_MOISTURE = "moisture_data"
        const val EXTRA_IMAGE = "extra_image"
    }
}