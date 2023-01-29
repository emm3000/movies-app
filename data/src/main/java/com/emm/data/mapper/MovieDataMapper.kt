package com.emm.data.mapper

import com.emm.data.api.response.MovieResponse
import com.emm.data.localdatabase.entity.MovieEntity
import com.emm.domain.entities.MovieModel

interface MovieDataMapper {

    fun mapMovieListResponseToDomainModel(movieResponse: MovieResponse): MovieModel

    fun mapMoviesListEntityToDomainModel(movieEntity: MovieEntity): MovieModel

    fun mapMovieListResponseToEntity(movieResponse: MovieResponse): MovieEntity
}
