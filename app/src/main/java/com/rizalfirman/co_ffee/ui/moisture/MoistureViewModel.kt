package com.rizalfirman.co_ffee.ui.moisture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rizalfirman.co_ffee.config.repo.RepositoryMoisture
import java.io.File

class MoistureViewModel(private val repository: RepositoryMoisture): ViewModel() {

    private var _file = MutableLiveData<File>()
    private val file: LiveData<File> get() =  _file

    fun getFile(file: File){
        this._file.value = file
    }
    val predictMoisture = Transformations.switchMap(file){ pic ->
        repository.uploadImage(pic)
    }
}