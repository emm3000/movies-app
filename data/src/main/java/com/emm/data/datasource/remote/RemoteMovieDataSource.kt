package com.emm.data.datasource.remote

import com.emm.core.Result
import com.emm.data.api.response.MoviesResponse

interface RemoteMovieDataSource {

    suspend fun getMoviesList(): Result<MoviesResponse>
}
