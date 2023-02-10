package com.emm.core

/**
 * Clase sellada, para representar los diferentes tipos de errores
 */
sealed class Failure(
    val message: String,
    val error: Throwable?,
) {

    constructor(message: String) : this(message, null)

    class NetworkError(
        message: String,
        error: Throwable? = null,
    ) : Failure(message, error)

    class LocalDataBaseError(
        message: String,
        error: Throwable? = null,
    ) : Failure(message, error)

    object None : Failure(message = "")
}
