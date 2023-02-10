package com.emm.moviesapp.utils

import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.log(color: Color = Color.Red) = this.border(width = 1.dp, color = color)
