package com.loogika.mikroisp.uikit.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.loogika.mikroisp.core.function.guardLetOrEmpty
import com.loogika.mikroisp.uikit.text.LTextHeadingSemiBoldSubTitle

@Composable
fun LTopAppBar(
    action: String? = null,
    showBackButton: Boolean = true,
    icon: ImageVector = Icons.Filled.ArrowBack,
    actionListener: () -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, top = 16.dp, bottom = 16.dp)
    ) {
        val (backButton, actionTopAppBar) = createRefs()

        if (showBackButton) {
            IconButton(
                onClick = onBackPressed,
                modifier = Modifier
                    .constrainAs(backButton) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .height(20.dp)
                    .width(20.dp)
            ) {
                Icon(
                    icon,
                    contentDescription = "backButton",
                    tint = MaterialTheme.colors.secondary
                )
            }
        }

        guardLetOrEmpty(action) {
            LTextHeadingSemiBoldSubTitle(
                modifier = Modifier
                    .constrainAs(actionTopAppBar) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                    .clickable(
                        onClick = actionListener
                    )
                    .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp),
                text = it,
                color = MaterialTheme.colors.secondary
            )
        }
    }
}