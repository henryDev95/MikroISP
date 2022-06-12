package com.loogika.mikroisp.uikit.divider

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LDivider(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(
        top = 16.dp,
        end = 16.dp,
        start = 16.dp,
        bottom = 16.dp
    )
) {
    Divider(
        modifier = modifier.padding(paddingValues),
        color = MaterialTheme.colors.secondary,
        thickness = 1.dp,
    )
}