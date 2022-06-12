package com.rizalfirman.co_ffee.config.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.rizalfirman.co_ffee.config.ConfigResult
import com.rizalfirman.co_ffee.config.response.Response
import com.rizalfirman.co_ffee.config.retrofit.ApiService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class Repository(private val apiService: ApiService) {

    fun uploadImage (imageMultiPart: File): LiveData<ConfigResult<Response>> = liveData {
        emit(ConfigResult.Loading)

        try {
            val part = MultipartBody.Part.createFormData(
                "file",
                imageMultiPart.name,
                imageMultiPart.asRequestBody("file/*".toMediaType()))
            val deteksi = apiService.diagnose(part)
            Log.d("aaasup",deteksi.toString())
            emit(ConfigResult.Success(deteksi))
        }catch (e : Exception){
            e.printStackTrace()
            Log.d("Repository", "UploadImage : ${e.message.toString()}")
            emit(ConfigResult.Error(e.message.toString()))
        }
    }

    companion object{
        @Volatile
        private var instance : Repository? = null
        fun getInstance(
            apiService: ApiService
        ): Repository =
            instance ?: synchronized(this){
                instance ?: Repository(apiService)
            }.also { instance = it }
    }
}