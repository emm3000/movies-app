package com.emm.core

sealed class Result<out T>
data class Success<out T>(val data: T) : Result<T>()
data class Error(val error: Throwable) : Result<Nothing>()
