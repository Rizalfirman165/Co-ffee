package com.rizalfirman.co_ffee.config.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlantDisease (
    var name : String,
    var deskripsi : String,
    var photo: Int
        ): Parcelable