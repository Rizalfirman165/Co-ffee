package com.rizalfirman.co_ffee.config

sealed class ConfigResult<out T> private constructor(){

    data class Success<out T>(val data: T): ConfigResult<T>()
    data class Error (val error: String): ConfigResult<Nothing>()
    object Loading : ConfigResult<Nothing>()
}
