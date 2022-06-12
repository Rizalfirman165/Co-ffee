package com.rizalfirman.co_ffee.di

import android.content.Context
import com.rizalfirman.co_ffee.config.repo.Repository
import com.rizalfirman.co_ffee.config.retrofit.ApiConfig


object InjectionDeteksi {

    fun provideRepository(context: Context):
            Repository {
        val apiService = ApiConfig.getApiService()

        return Repository.getInstance(apiService)
    }
}