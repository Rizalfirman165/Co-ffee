package com.rizalfirman.co_ffee.ui.moisture

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rizalfirman.co_ffee.config.repo.RepositoryMoisture
import com.rizalfirman.co_ffee.di.InjectionMoisture

class MoistureViewModelFactory private constructor(private val moistureRepository: RepositoryMoisture): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MoistureViewModel::class.java) -> {
                MoistureViewModel(moistureRepository) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel Class : " + modelClass.name)
            }
        }
    }

    companion object{
        @Volatile
        private var instance: MoistureViewModelFactory? = null
        fun getInstance(context: Context): MoistureViewModelFactory =
            instance ?: synchronized(this){
                instance ?: MoistureViewModelFactory(InjectionMoisture.provideMoisture(context))
            }.also { instance = it }
    }
}