package com.emm.core

/**
 * Entidad, que permite manejar dos tipos de resultados o tipos,
 * Success -> con el valor esperado
 * Error -> Con una excepción.
 */
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: Throwable? = null) : Result<Nothing>()
}

/**
 * Extensión para mapear los modelos entre la paca de dominio y data, según el resultado,
 * de la consulta a la fuente de datos.
 */
fun <T, X> Result<T>.mapper(mapper: (T) -> X): Result<X> {
    return when (this) {
        is Result.Error -> Result.Error(this.error)
        is Result.Success -> Result.Success(mapper(this.data))
    }
}