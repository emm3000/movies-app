package com.emm.moviesapp.mapper

import com.emm.domain.entities.MovieModel
import com.emm.moviesapp.model.MovieUI

interface MovieUIMapper {

    fun mapMovieListDomainToUI(movieModel: MovieModel): MovieUI

}