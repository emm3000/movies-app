package com.emm.moviesapp.fragments.moviedetail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emm.moviesapp.R
import com.emm.moviesapp.fragments.moviedetail.theme.fontFamily
import com.emm.moviesapp.model.MovieUI

@Composable
fun MovieItem(movie: MovieUI) {
    Column(
        modifier = Modifier.width(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CoilImage(urlImg = movie.image, debugPreview = R.drawable.test_img)
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = movie.title,
            maxLines = 2,
            fontSize = 13.sp,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(40.dp)
                .padding(horizontal = 10.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        MovieItem(
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
