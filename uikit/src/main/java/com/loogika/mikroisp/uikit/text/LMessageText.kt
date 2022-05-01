package com.loogika.mikroisp.uikit.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loogika.mikroisp.uikit.style.*
import com.loogika.mikroisp.uikit.text.enum.Type

@Composable
fun LMessageText(message: String, type: Type) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 10.dp)) {
        Text(
            text = message,
            color = when(type){
                Type.INFO -> MaterialTheme.colors.onSecondary
                Type.SUCCESS -> MaterialTheme.colors.onPrimary
                Type.WARNING -> MaterialTheme.colors.onSecondary
                Type.ERROR -> MaterialTheme.colors.onError
            },
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}