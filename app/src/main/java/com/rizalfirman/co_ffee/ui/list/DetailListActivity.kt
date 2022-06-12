package com.rizalfirman.co_ffee.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rizalfirman.co_ffee.config.model.PlantDisease
import com.rizalfirman.co_ffee.databinding.ActivityDetailListBinding

class DetailListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iconBack.setOnClickListener {
            intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
            finish()
        }

        val pindahData = intent.getParcelableExtra<PlantDisease>(ListActivity.INTENT_DATA)
        if (pindahData != null){
            binding.imgDetail.setImageResource(pindahData.photo)
            binding.nameDetail.text = pindahData.name
            binding.desckripDetail.text = pindahData.deskripsi
        }
    }
}