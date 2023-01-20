package com.emm.moviesapp.mapper

import com.emm.domain.entities.MovieEntity
import com.emm.moviesapp.model.MovieModel

interface MovieUIMapper {

    fun mapMovieListDomainToUI(movieEntity: MovieEntity): MovieModel

}