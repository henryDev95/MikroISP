package com.loogika.mikroisp.login.ui.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.hapicorp.imhapi.login.components.view.LoginContent
import com.loogika.mikroisp.login.ui.view.LoginHeader
import com.loogika.mikroisp.login.viewmodel.LoginViewModel

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize().padding(it)) {
            val (header, content) = createRefs()
            LoginHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(header) {
                        top.linkTo(parent.top, margin = 16.dp)
                        bottom.linkTo(content.top)
                    }
            )

            LoginContent(
                modifier = Modifier.constrainAs(content) {
                    top.linkTo(header.bottom, margin = 16.dp)
                    bottom.linkTo(parent.bottom)
                },
                viewModel = viewModel,
            )
        }
    }
}
