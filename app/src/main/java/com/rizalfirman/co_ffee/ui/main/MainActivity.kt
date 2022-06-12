package com.rizalfirman.co_ffee.ui.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.rizalfirman.co_ffee.R
import com.rizalfirman.co_ffee.databinding.ActivityMainBinding
import com.rizalfirman.co_ffee.ui.moisture.MoistureActivity
import com.rizalfirman.co_ffee.ui.deteksi.DeteksiActivity
import com.rizalfirman.co_ffee.ui.list.ListActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var backPres = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.deteksi.setOnClickListener {
            val intentDeteksi = Intent(this, DeteksiActivity::class.java)
            startActivity(intentDeteksi)
            finish()
        }

        binding.moisture.setOnClickListener {
            val intentMoisture = Intent(this, MoistureActivity::class.java)
            startActivity(intentMoisture)
            finish()
        }
        binding.daftar.setOnClickListener {
            val intentDaftar = Intent(this, ListActivity::class.java)
            startActivity(intentDaftar)
            finish()
        }

        val toolbar = findViewById<MaterialToolbar>(R.id.topBar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.dashboard)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_setting, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_setting -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                true
            }
            else -> true
        }
    }


    override fun onBackPressed() {
        if (backPres + 2000 > System.currentTimeMillis()){
            super.onBackPressed()

        }
        else{
            Toast.makeText(applicationContext, resources.getText(R.string.exit), Toast.LENGTH_SHORT).show()
            backPres = System.currentTimeMillis()
        }


    }
}