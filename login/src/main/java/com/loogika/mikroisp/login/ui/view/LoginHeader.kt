package com.loogika.mikroisp.login.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.loogika.mikroisp.core.R
import com.loogika.mikroisp.uikit.style.Typography


@Composable
fun LoginHeader(modifier: Modifier) {

    Column(modifier = modifier) {

        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_user),
            contentDescription = "Launcher",
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.CenterHorizontally),
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(R.string.login_header_text),
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

    }
}