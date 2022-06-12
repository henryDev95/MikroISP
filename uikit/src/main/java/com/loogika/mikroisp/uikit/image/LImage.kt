package com.loogika.mikroisp.uikit.image

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.loogika.mikroisp.uikit.R
import com.loogika.mikroisp.uikit.style.MikroISPTheme

@Composable
fun LImage(
    modifier: Modifier = Modifier,
    urlImage: String?
) {
    Image(
        modifier = modifier,
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = urlImage).apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
                transformations(
                    RoundedCornersTransformation(10f)
                )
                placeholder(
                    R.drawable.ic_placeholder
                )
                error(
                    R.drawable.ic_placeholder
                )
            }).build()
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Preview("default", "image")
@Preview("dark theme", "image", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun ButtonPreview() {
    MikroISPTheme {
        LImage(modifier = Modifier.size(70.dp), urlImage = "unknow")
    }
}