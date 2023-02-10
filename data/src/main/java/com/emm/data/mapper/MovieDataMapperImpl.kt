package com.emm.data.mapper

import com.emm.data.api.response.MovieResponse
import com.emm.data.localdatabase.entity.MovieEntity
import com.emm.domain.entities.MovieModel
import javax.inject.Inject

class MovieDataMapperImpl @Inject constructor() : MovieDataMapper {

    override fun mapMovieListResponseToDomainModel(movieResponse: MovieResponse): MovieModel {
        return MovieModel(
            contentRating = movieResponse.contentRating,
            directors = movieResponse.directors,
            fullTitle = movieResponse.fullTitle,
            genres = movieResponse.genres,
            id = movieResponse.id,
            image = movieResponse.image,
            plot = movieResponse.plot,
            releaseState = movieResponse.releaseState,
            stars = movieResponse.stars,
            title = movieResponse.title,
            year = movieResponse.year,
        )
    }

    override fun mapMoviesListEntityToDomainModel(movieEntity: MovieEntity): MovieModel {
        return MovieModel(
            contentRating = movieEntity.contentRating,
            directors = movieEntity.directors,
            fullTitle = movieEntity.fullTitle,
            genres = movieEntity.genres,
            id = movieEntity.id,
            image = movieEntity.image,
            plot = movieEntity.plot,
            releaseState = movieEntity.releaseState,
            stars = movieEntity.stars,
            title = movieEntity.title,
            year = movieEntity.year,
        )
    }

    override fun mapMovieListResponseToEntity(movieResponse: MovieResponse): MovieEntity {
        return MovieEntity(
            contentRating = movieResponse.contentRating,
            directors = movieResponse.directors,
            fullTitle = movieResponse.fullTitle,
            genres = movieResponse.genres,
            id = movieResponse.id,
            image = movieResponse.image,
            plot = movieResponse.plot,
            releaseState = movieResponse.releaseState,
            stars = movieResponse.stars,
            title = movieResponse.title,
            year = movieResponse.year,
        )
    }
}
