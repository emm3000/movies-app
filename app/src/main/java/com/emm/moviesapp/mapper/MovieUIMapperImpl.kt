package com.emm.moviesapp.mapper

import com.emm.domain.entities.MovieEntity
import com.emm.moviesapp.model.DirectorModel
import com.emm.moviesapp.model.GenreModel
import com.emm.moviesapp.model.MovieModel
import com.emm.moviesapp.model.StarModel
import javax.inject.Inject

class MovieUIMapperImpl @Inject constructor() : MovieUIMapper {

    override fun mapMovieListDomainToUI(movieEntity: MovieEntity): MovieModel {
        return MovieModel(
            contentRating = movieEntity.contentRating,
            directorList = movieEntity.directorList.map {
                DirectorModel(
                    id = it.id,
                    name = it.name
                )
            },
            directors = movieEntity.directors,
            fullTitle = movieEntity.fullTitle,
            genreList = movieEntity.genreList.map {
                GenreModel(
                    key = it.key,
                    value = it.value
                )
            },
            genres = movieEntity.genres,
            id = movieEntity.id,
            imDbRating = movieEntity.imDbRating,
            imDbRatingCount = movieEntity.imDbRatingCount,
            image = movieEntity.image,
            metacriticRating = movieEntity.metacriticRating,
            plot = movieEntity.plot,
            releaseState = movieEntity.releaseState,
            runtimeStr = movieEntity.runtimeStr,
            runtimeMins = movieEntity.runtimeMins,
            starList = movieEntity.starList.map {
                StarModel(
                    id = it.id,
                    name = it.name
                )
            },
            stars = movieEntity.stars,
            title = movieEntity.title,
            year = movieEntity.year
        )
    }

}