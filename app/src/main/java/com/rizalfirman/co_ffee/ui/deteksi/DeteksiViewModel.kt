package com.rizalfirman.co_ffee.ui.deteksi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rizalfirman.co_ffee.config.repo.Repository
import java.io.File


class DeteksiViewModel(private val repository: Repository) : ViewModel() {
    private var _file = MutableLiveData<File>()
    private val file: LiveData<File> get() =  _file

    fun getFile(file: File){
        this._file.value = file
    }
//    fun uploadImage(imageMultipart: MultipartBody.Part) = repository.uploadImage(imageMultipart)
    val predict = Transformations.switchMap(file){ pic ->
        repository.uploadImage(pic)
    }

}