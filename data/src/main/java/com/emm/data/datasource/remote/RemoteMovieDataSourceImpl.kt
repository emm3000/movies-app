package com.emm.data.datasource.remote

import com.emm.core.Result
import com.emm.data.api.ApiClient
import com.emm.data.api.response.MoviesResponse
import com.emm.data.utils.safeApiCall
import javax.inject.Inject

class RemoteMovieDataSourceImpl @Inject constructor(
    private val apiClient: ApiClient,
) : RemoteMovieDataSource {

    override suspend fun getMoviesList(): Result<MoviesResponse> {
        return safeApiCall { apiClient.getMoviesList() }
    }
}
