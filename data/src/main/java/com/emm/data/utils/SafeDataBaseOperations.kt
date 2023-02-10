package com.emm.data.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

suspend inline fun <T> safeDataBaseOperations(
    defaultValue: T,
    crossinline dbOperation: suspend () -> T,
): T {
    return withContext(Dispatchers.IO) {
        try {
            dbOperation()
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }
}
