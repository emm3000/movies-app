package com.emm.data.datasource

import com.emm.core.Result
import com.emm.data.response.MoviesResponse

interface RemoteMovieDataSource {

    suspend fun getMoviesList(): Result<MoviesResponse>

}