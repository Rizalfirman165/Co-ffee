package com.rizalfirman.co_ffee.ui.list

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rizalfirman.co_ffee.R
import com.rizalfirman.co_ffee.config.adapter.ListAdapter
import com.rizalfirman.co_ffee.config.model.PlantDisease
import com.rizalfirman.co_ffee.databinding.ActivityListBinding
import com.rizalfirman.co_ffee.ui.main.MainActivity

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private lateinit var rvPlatDisease: RecyclerView
    private val list = ArrayList<PlantDisease>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvPlatDisease = binding.rvDisease
        rvPlatDisease.setHasFixedSize(true)
        list.addAll(listDiseases)
        showRecycleList()

        binding.iconBack.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private val listDiseases: ArrayList<PlantDisease>
    get() {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listDisease = ArrayList<PlantDisease>()
        for (i in dataName.indices){
            val disease = PlantDisease(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
            listDisease.add(disease)
        }
        return listDisease
    }

    private fun showRecycleList(){
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            rvPlatDisease.layoutManager = GridLayoutManager(this, 2)
        }else {
            rvPlatDisease.layoutManager = LinearLayoutManager(this)
        }

        val diseaseAdapter = ListAdapter(list) { disease ->
            val intent = Intent(this, DetailListActivity::class.java)
            intent.putExtra(INTENT_DATA, disease)
            startActivity(intent)
        }
        rvPlatDisease.adapter = diseaseAdapter
    }

    companion object{
        val INTENT_DATA = "intent_data"
    }
}