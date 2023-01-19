package com.emm.core

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: Throwable) : Result<Nothing>()
}

fun <T, X> Result<T>.mapper(mapper: (T) -> X): Result<X> {
    return when (this) {
        is Result.Error -> Result.Error(this.error)
        is Result.Success -> Result.Success(mapper(this.data))
    }
}