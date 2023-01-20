package com.emm.moviesapp.mapper

import com.emm.domain.entities.MovieModel
import com.emm.moviesapp.model.MovieUI
import javax.inject.Inject

class MovieUIMapperImpl @Inject constructor() : MovieUIMapper {

    override fun mapMovieListDomainToUI(movieModel: MovieModel): MovieUI {
        return MovieUI(
            contentRating = movieModel.contentRating,
            directors = movieModel.directors,
            fullTitle = movieModel.fullTitle,
            genres = movieModel.genres,
            id = movieModel.id,
            image = movieModel.image,
            plot = movieModel.plot,
            releaseState = movieModel.releaseState,
            stars = movieModel.stars,
            title = movieModel.title,
            year = movieModel.year
        )
    }

}