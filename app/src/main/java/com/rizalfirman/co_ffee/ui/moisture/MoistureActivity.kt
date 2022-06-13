package com.rizalfirman.co_ffee.ui.moisture

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.rizalfirman.co_ffee.R
import com.rizalfirman.co_ffee.config.ConfigResult
import com.rizalfirman.co_ffee.config.Media
import com.rizalfirman.co_ffee.databinding.ActivityMoistureBinding
import com.rizalfirman.co_ffee.ui.main.MainActivity
import com.rizalfirman.co_ffee.ui.result.ResultActivity
import com.rizalfirman.co_ffee.ui.result.ResultMoistureActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class MoistureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoistureBinding
    private lateinit var currentPhotoPath: String
    private lateinit var moistureModel: MoistureViewModel

    private var imageFile: Uri? = null
    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoistureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        binding.iconBack.setOnClickListener {
            val moveIntent = Intent(this, MainActivity::class.java)
            startActivity(moveIntent)
        }

        binding.btnCamera.setOnClickListener {
            TakePhoto()
        }

        binding.btnGallery.setOnClickListener {
            onGallery()
        }

        binding.btnScanning.setOnClickListener {
            if (binding.previewImageView.drawable.constantState == resources.getDrawable(R.drawable.contoh).constantState){
                Toast.makeText(this, R.string.message_validation, Toast.LENGTH_SHORT).show()
            } else{

                uploadPhoto()
            }



        }
    }

    private fun onGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"

        val chooser = Intent.createChooser(intent, resources.getString(R.string.choose_picture))
        tampilIntentGallery.launch(chooser)

    }

    private val tampilIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
            result ->
        if (result.resultCode == RESULT_OK){
            binding.textView.visibility = View.GONE
            val selectedImg: Uri = result.data?.data as Uri
            imageFile = selectedImg
            val myFile = Media.uriToFile(selectedImg, this@MoistureActivity)
            getFile = myFile
            moistureModel.getFile(myFile)
            binding.previewImageView.setImageURI(selectedImg)
        }
    }

    @SuppressLint("QueryPermissionNeeded")
    private fun TakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)
        Media.createTempFile(application).also {
            val photoUri: Uri = FileProvider.getUriForFile(this@MoistureActivity, "com.rizalfirman.co_ffee", it)
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            tampilIntentCamera.launch(intent)
        }

    }
    private val tampilIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if (it.resultCode == RESULT_OK){
            binding.textView.visibility = View.GONE
            val myFile = File(currentPhotoPath)
            getFile = myFile
            val result = BitmapFactory.decodeFile(myFile.path)
            binding.previewImageView.setImageBitmap(result)
        }
    }

    @SuppressLint("ResourceType")
    private fun uploadPhoto(){
        if (getFile != null) {
            binding.progressBar.visibility = View.VISIBLE
            val file = Media.reduceFileImage(getFile as File)
            val requstImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultiPart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo",
                file.name,
                requstImageFile
            )

            moistureModel.predictMoisture.observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is ConfigResult.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            getWindow().setFlags(
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        }
                        is ConfigResult.Success -> {
                            Log.d("asup ", result.data.toString())
                            binding.progressBar.visibility = View.GONE
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            val intent =
                                Intent(this@MoistureActivity, ResultMoistureActivity::class.java).apply {
                                    putExtra(
                                        ResultMoistureActivity.DATA_MOISTURE,
                                        result.data

                                    )
                                    putExtra(
                                        ResultMoistureActivity.EXTRA_IMAGE,
                                        imageFile.toString()

                                    )
                                }

                            startActivity(intent)
                            Toast.makeText(this, result.data.kadarAir, Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        is ConfigResult.Error -> {
                            binding.progressBar.visibility = View.GONE
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            Toast.makeText(this, "Failure : " + result.error, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

            }
        }

    }

    private fun setupViewModel(){
        val factory : MoistureViewModelFactory = MoistureViewModelFactory.getInstance(this)
        moistureModel = ViewModelProvider(this, factory)[MoistureViewModel::class.java]
    }
}