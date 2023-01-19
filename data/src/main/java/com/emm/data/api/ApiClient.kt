package com.emm.data.api

import com.emm.data.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET
    suspend fun getMoviesList(): Response<MoviesResponse>
}