package com.emm.data.utils

import com.emm.core.Failure
import com.emm.core.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception

suspend inline fun <T> safeApiCall(
    crossinline apiCall: suspend () -> Response<T>,
): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            val result = apiCall()
            if (result.isSuccessful && result.body() != null) {
                Result.Success(data = result.body()!!)
            } else {
                Result.Error(Failure.NetworkError(message = "HttpException, -> message"))
            }
        } catch (e: Exception) {
            Result.Error(Failure.None)
        }
    }
}
