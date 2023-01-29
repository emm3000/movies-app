package com.emm.moviesapp.fragments.moviedetail.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.emm.moviesapp.fragments.moviedetail.theme.fontFamily

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp,
) {
    Text(
        fontWeight = FontWeight.Bold,
        text = text,
        fontSize = fontSize,
        maxLines = 1,
        modifier = modifier,
        fontFamily = fontFamily,
        overflow = TextOverflow.Ellipsis,
        color = Color.White,
    )
}
