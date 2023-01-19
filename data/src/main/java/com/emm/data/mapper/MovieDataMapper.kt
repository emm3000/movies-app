package com.emm.data.mapper

import com.emm.data.response.MovieResponse
import com.emm.domain.entities.MovieEntity

interface MovieDataMapper {

    fun mapMovieListResponseToDomainEntity(list: List<MovieResponse>): List<MovieEntity>

}