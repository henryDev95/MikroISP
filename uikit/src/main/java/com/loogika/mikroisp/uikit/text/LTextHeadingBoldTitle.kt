package com.loogika.mikroisp.uikit.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun LTextHeadingBoldTitle(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = MaterialTheme.colors.primary,
    style: TextStyle = MaterialTheme.typography.h1,
    maxLines: Int = 3,
    text: String? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        text = text ?: "",
        modifier = modifier,
        style = style,
        textAlign = textAlign,
        color = color,
        maxLines = maxLines,
        overflow = overflow
    )
}