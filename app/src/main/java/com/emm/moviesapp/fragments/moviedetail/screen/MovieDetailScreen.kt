package com.emm.moviesapp.fragments.moviedetail.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emm.moviesapp.R
import com.emm.moviesapp.fragments.moviedetail.MovieDetailViewModel
import com.emm.moviesapp.fragments.moviedetail.components.CoilImage
import com.emm.moviesapp.fragments.moviedetail.components.LabelText
import com.emm.moviesapp.fragments.moviedetail.components.MovieItem
import com.emm.moviesapp.fragments.moviedetail.components.TitleText
import com.emm.moviesapp.fragments.moviedetail.state.MovieDetailState
import com.emm.moviesapp.model.MovieUI

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel,
    onBackButtonAction: () -> Unit = {},
) {
    val viewState: MovieDetailState by viewModel.viewState.collectAsState()
    MovieDetail(
        viewState = viewState,
        onBackButtonAction = onBackButtonAction,
    )
}

@Composable
private fun MovieDetail(viewState: MovieDetailState, onBackButtonAction: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.gray)),
    ) {
        if (viewState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )
        }
        AnimatedVisibility(
            visible = !viewState.isLoading,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000)),
        ) {
            MovieDetailBody(
                movie = viewState.currentMovie,
                similarGenresMovies = viewState.similarGenres,
                similarDirectorsMovies = viewState.similarDirectors,
                similarStarsMovies = viewState.similarStars,
                onBackButtonAction = onBackButtonAction,
            )
        }
    }
}

@Composable
private fun MovieDetailBody(
    movie: MovieUI?,
    similarGenresMovies: List<MovieUI> = emptyList(),
    similarDirectorsMovies: List<MovieUI> = emptyList(),
    similarStarsMovies: List<MovieUI> = emptyList(),
    onBackButtonAction: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .background(color = colorResource(id = R.color.gray)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderDetail(
            movieTitle = movie?.title.orEmpty(),
            onBackButtonAction = onBackButtonAction,
        )

        Spacer(modifier = Modifier.height(20.dp))

        CoilImage(urlImg = movie?.image.orEmpty())

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(20.dp),
        ) {
            LabelText(label = stringResource(R.string.lbl_title), value = movie?.fullTitle)
            LabelText(label = stringResource(R.string.lbl_directors), value = movie?.directors)
            LabelText(label = stringResource(R.string.lbl_release_state), value = movie?.releaseState)
            LabelText(label = stringResource(R.string.lbl_description), value = movie?.plot)
            LabelText(label = stringResource(R.string.lbl_content_rating), value = movie?.contentRating)
        }

        MovieListOfSimilarFeatures(
            similarMovies = similarGenresMovies,
            title = stringResource(R.string.lbl_similar_genres),
        )
        MovieListOfSimilarFeatures(
            similarMovies = similarDirectorsMovies,
            title = stringResource(R.string.lbl_similar_directors),
        )
        MovieListOfSimilarFeatures(
            similarMovies = similarStarsMovies,
            title = stringResource(R.string.lbl_similar_stars),
        )
    }
}

@Composable
private fun HeaderDetail(
    movieTitle: String?,
    onBackButtonAction: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(10.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.7f)
                .align(Alignment.Center),
            contentAlignment = Alignment.Center,
        ) {
            TitleText(text = movieTitle.orEmpty())
        }
        IconButton(onClick = { onBackButtonAction() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.img_des_back_arrow),
                tint = Color.White,
            )
        }
    }
}

@Composable
private fun MovieListOfSimilarFeatures(
    similarMovies: List<MovieUI>,
    title: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
    ) {
        TitleText(
            text = title,
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 20.dp),
        )

        Spacer(modifier = Modifier.heightIn(6.dp))

        LazyRow(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(similarMovies) { movie ->
                MovieItem(movie = movie)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        MovieDetailBody(
            movie = MovieUI(
                contentRating = "R",
                directors = "Random, Random, Random",
                fullTitle = "Title Movie (123)",
                genres = "",
                id = "",
                image = "",
                plot = "",
                releaseState = "xx xx xx",
                stars = "",
                title = "Title Movie",
                year = "",
            ),
        )
    }
}
