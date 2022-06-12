package com.rizalfirman.co_ffee.ui.result

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rizalfirman.co_ffee.config.response.Response
import com.rizalfirman.co_ffee.config.response.ResponseMoisture
import com.rizalfirman.co_ffee.databinding.ActivityResultBinding
import com.rizalfirman.co_ffee.ui.deteksi.DeteksiActivity
import com.rizalfirman.co_ffee.ui.main.MainActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getParcelableExtra<Response>(EXTRA_DATA)
        val resultMoisture = intent.getParcelableExtra<ResponseMoisture>(DATA_MOISTURE)

        val resultImage = intent.getStringExtra(EXTRA_IMAGE)
//        val drawable : Drawable = resources.getDrawable(resultImage)
        Log.d("ss ", result.toString())
        binding.apply {
            tvResult.text = "Plant Disease \n ${result?.diagnose}"
            tvResult.text = "Moisture Level tester \n${resultMoisture?.kadarAir}"

        }
        binding.previewImageView.setImageURI(Uri.parse(resultImage))
        Log.d("munculgeas ", resultImage.toString())


        binding.iconBack.setOnClickListener {
            val movIntent = Intent(this, MainActivity::class.java)
            startActivity(movIntent)
            finish()
        }


    }

    companion object {
        const val DATA_MOISTURE = "moisture_data"
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_IMAGE = "extra_image"
    }
}