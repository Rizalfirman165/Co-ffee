package com.rizalfirman.co_ffee.ui.deteksi

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rizalfirman.co_ffee.config.repo.Repository
import com.rizalfirman.co_ffee.di.InjectionDeteksi

class DeteksiViewModelFactory private constructor(private val deteksiRepository: Repository): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DeteksiViewModel::class.java) -> {
                DeteksiViewModel(deteksiRepository) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel Class : " + modelClass.name)

            }
        }
    }
    companion object{
        @Volatile
        private var instance: DeteksiViewModelFactory? = null
        fun getInstance(context: Context): DeteksiViewModelFactory =
            instance ?: synchronized(this){
                instance ?: DeteksiViewModelFactory(InjectionDeteksi.provideRepository(context))
            }.also { instance = it }
    }
}