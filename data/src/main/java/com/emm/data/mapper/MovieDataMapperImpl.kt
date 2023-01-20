package com.emm.data.mapper

import com.emm.data.response.MovieResponse
import com.emm.domain.entities.DirectorEntity
import com.emm.domain.entities.GenreEntity
import com.emm.domain.entities.MovieEntity
import com.emm.domain.entities.StarEntity
import javax.inject.Inject

class MovieDataMapperImpl @Inject constructor() : MovieDataMapper {

    override fun mapMovieListResponseToDomainEntity(movieResponse: MovieResponse): MovieEntity {
        return MovieEntity(
            contentRating = movieResponse.contentRating,
            directorList = movieResponse.directorList.map {
                DirectorEntity(
                    id = it.id,
                    name = it.name
                )
            },
            directors = movieResponse.directors,
            fullTitle = movieResponse.fullTitle,
            genreList = movieResponse.genreList.map {
                GenreEntity(
                    key = it.key,
                    value = it.value
                )
            },
            genres = movieResponse.genres,
            id = movieResponse.id,
            imDbRating = movieResponse.imDbRating,
            imDbRatingCount = movieResponse.imDbRatingCount,
            image = movieResponse.image,
            metacriticRating = movieResponse.metacriticRating,
            plot = movieResponse.plot,
            releaseState = movieResponse.releaseState,
            runtimeStr = movieResponse.runtimeStr,
            runtimeMins = movieResponse.runtimeMins,
            starList = movieResponse.starList.map {
                StarEntity(
                    id = it.id,
                    name = it.name
                )
            },
            stars = movieResponse.stars,
            title = movieResponse.title,
            year = movieResponse.year
        )
    }
}