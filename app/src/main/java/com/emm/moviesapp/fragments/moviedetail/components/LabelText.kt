package com.emm.moviesapp.fragments.moviedetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.emm.moviesapp.R
import com.emm.moviesapp.fragments.moviedetail.theme.fontFamily

@Composable
fun LabelText(
    label: String,
    value: String?,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = fontFamily,
                    ),
                ) {
                    append("$label : ")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.grayAEAEAE),
                        fontFamily = fontFamily,
                    ),
                ) {
                    append(value.orEmpty())
                }
            },
            modifier = Modifier
                .align(Alignment.CenterVertically),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LabelTextPreview() {
    Box(modifier = Modifier.background(color = colorResource(id = R.color.gray))) {
        LabelText(label = "Description", value = "Random Message")
    }
}
