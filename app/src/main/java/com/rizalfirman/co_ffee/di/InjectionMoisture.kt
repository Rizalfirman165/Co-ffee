package com.rizalfirman.co_ffee.di

import android.content.Context
import com.rizalfirman.co_ffee.config.repo.RepositoryMoisture
import com.rizalfirman.co_ffee.config.retrofit.ApiMoisture

object InjectionMoisture {

    fun provideMoisture(context: Context):
            RepositoryMoisture {
        val apiService = ApiMoisture.getApiService()

        return RepositoryMoisture.getInstance(apiService)
    }
}