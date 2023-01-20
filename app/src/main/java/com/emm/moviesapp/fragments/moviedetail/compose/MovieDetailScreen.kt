package com.emm.moviesapp.fragments.moviedetail.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.emm.moviesapp.R
import com.emm.moviesapp.fragments.moviedetail.MovieDetailViewModel
import com.emm.moviesapp.model.MovieUI

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel,
    onBackButtonAction: () -> Unit = {}
) {

    val movie: MovieUI? by viewModel.movieDetail.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
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
                contentAlignment = Alignment.Center
            ) {
                Text(
                    fontWeight = FontWeight.ExtraBold,
                    text = movie?.fullTitle.orEmpty(),
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            IconButton(onClick = { onBackButtonAction() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie?.image)
                .error(R.drawable.ic_launcher_background)
                .crossfade(true)
                .build(),
            contentDescription = "Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .widthIn(150.dp)
                .clip(RoundedCornerShape(5.dp))
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(20.dp)
        ) {
            LabelText(label = "Directors", value = movie?.directors)
            LabelText(label = "Release State", value = movie?.releaseState)
            LabelText(label = "Description", value = movie?.plot)
        }
    }

}

@Composable
fun LabelText(
    label: String,
    value: String?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("$label : ")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append(value.orEmpty())
                }
            },
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
    }
}