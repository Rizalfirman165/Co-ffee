package com.rizalfirman.co_ffee.config.retrofit

import com.rizalfirman.co_ffee.config.response.Response
import com.rizalfirman.co_ffee.config.response.ResponseMoisture
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {


    @POST("desease")
    @Multipart
    suspend fun diagnose(
        @Part file: MultipartBody.Part,

    ): Response

    @POST("moisture")
    @Multipart
    suspend fun moisture(
        @Part file: MultipartBody.Part
    ): ResponseMoisture

}