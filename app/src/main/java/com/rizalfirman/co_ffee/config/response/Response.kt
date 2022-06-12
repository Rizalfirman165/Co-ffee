package com.rizalfirman.co_ffee.config.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Response(

	@field:SerializedName("diagnose")
	var diagnose: String
):Parcelable
