package com.loogika.mikroisp.uikit.button

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loogika.mikroisp.uikit.style.MikroISPTheme

@Composable
fun LButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(12.dp),
    border: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    background: Color = MaterialTheme.colors.primary,
    disabledBackground: Color = MaterialTheme.colors.onBackground
        .copy(alpha = 0.2f)
        .compositeOver(MaterialTheme.colors.background),
    text: String,
    textColor: Color = MaterialTheme.colors.background
) {
    TextButton(
        modifier = modifier
            .height(50.dp)
            .indication(
                interactionSource = interactionSource,
                indication = rememberRipple(),
            ),
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = if (enabled) background else disabledBackground
        ),
        shape = shape,
        border = border,
    ) {
        ProvideTextStyle(
            value = MaterialTheme.typography.body1,
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(),
                color = textColor,
            )
        }
    }
}



@Preview("default", "buttons")
@Preview("dark theme", "buttons", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun ButtonPreview() {
    MikroISPTheme {
        LButton(onClick = {}, text = "Button")
    }
}

@Preview("disabled", "buttons", showBackground = true)
@Composable
private fun ButtonDisabledPreview() {
    MikroISPTheme {
        LButton(onClick = {}, text = "Button", enabled = false)
    }
}