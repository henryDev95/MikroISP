package com.loogika.mikroisp.uikit.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun LTextHeadingSemiBoldSubTitle(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = MaterialTheme.colors.secondary,
    style: TextStyle = MaterialTheme.typography.subtitle1,
    maxLines: Int = 3,
    text: String?,
    overflow: TextOverflow = TextOverflow.Visible
) {
    Text(
        text = AnnotatedString(text = text ?: ""),
        modifier = modifier,
        style = style,
        textAlign = textAlign,
        color = color,
        maxLines = maxLines,
        overflow = overflow
    )
}