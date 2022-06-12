package com.rizalfirman.co_ffee.config.retrofit

import com.rizalfirman.co_ffee.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiMoisture {

    fun getApiService(): ApiService {
        val loggingInterceptor =
            if (BuildConfig.DEBUG){
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }

        val retrofitMoisture = Retrofit.Builder()
            .baseUrl("http://35.238.198.3:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitMoisture.create(ApiService::class.java)



    }
}