package com.rizalfirman.co_ffee.config.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.rizalfirman.co_ffee.config.ConfigResult
import com.rizalfirman.co_ffee.config.response.ResponseMoisture
import com.rizalfirman.co_ffee.config.retrofit.ApiService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class RepositoryMoisture(private val apiService: ApiService) {

    fun uploadImage (imageMultiPart: File): LiveData<ConfigResult<ResponseMoisture>> = liveData {
        emit(ConfigResult.Loading)

        try {
            val part = MultipartBody.Part.createFormData(
                "file",
                imageMultiPart.name,
                imageMultiPart.asRequestBody("file/*".toMediaType()))
            val moisture = apiService.moisture(part)
            Log.d("aasup",moisture.toString())
            emit(ConfigResult.Success(moisture))
        }catch (e : Exception){
            e.printStackTrace()
            Log.d("Repository", "UploadImage : ${e.message.toString()}")
            emit(ConfigResult.Error(e.message.toString()))
        }
    }

    companion object{
        @Volatile
        private var instance : RepositoryMoisture? = null
        fun getInstance(
            apiService: ApiService
        ): RepositoryMoisture =
            instance ?: synchronized(this){
                instance ?: RepositoryMoisture(apiService)
            }.also { instance = it }
    }
}