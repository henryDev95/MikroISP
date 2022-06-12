package com.loogika.mikroisp.client.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.loogika.mikroisp.clientdata.model.Client
import com.loogika.mikroisp.uikit.image.LImage
import com.loogika.mikroisp.uikit.text.LTextRegular

@Composable
fun ClientWidget(
    item: Client,
    paddingValues: PaddingValues = PaddingValues(
        start = 16.dp,
        end = 16.dp
    ),
    itemClickListener: () -> Unit = {},
) {
    val title = buildAnnotatedString {
        withStyle(style = MaterialTheme.typography.subtitle1.toSpanStyle()) {
            append("${item.dni} - ${item.userFirstName} ${item.userLastName}")
        }
        withStyle(style = MaterialTheme.typography.body1.toSpanStyle()) {
            append(" - ${item.phone1}")
        }
    }

    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .clickable(
                onClick = itemClickListener
            )
            .padding(paddingValues)
    ) {
        val (
            newsIcon,
            newsDetail,
        ) = createRefs()

        LImage(
            modifier = Modifier
                .constrainAs(newsIcon) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                }
                .size(70.dp),
            urlImage = item.address
        )

        Row(
            modifier = Modifier
                .constrainAs(newsDetail) {
                    start.linkTo(newsIcon.end, margin = 15.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        ) {
            LTextRegular(
                text = title
            )
        }
    }
}