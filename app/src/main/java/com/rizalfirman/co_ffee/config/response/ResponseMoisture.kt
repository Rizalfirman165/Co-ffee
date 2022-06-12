package com.rizalfirman.co_ffee.config.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseMoisture(

	@field:SerializedName("Kadar Air")
	val kadarAir: String
):Parcelable
