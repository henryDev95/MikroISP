package com.loogika.mikroisp.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loogika.mikroisp.client.uistate.ClientUiState
import com.loogika.mikroisp.client.viewmodel.ClientsViewModel
import com.loogika.mikroisp.client.widget.ClientWidget
import com.loogika.mikroisp.core.flow.rememberFlowWithLifecycle
import com.loogika.mikroisp.core.navigation.Screens
import com.loogika.mikroisp.uikit.divider.LDivider
import com.loogika.mikroisp.uikit.style.MikroISPTheme
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class DashboardActivity : ComponentActivity() {
    private var startDestination = Screens.SplashScreen.route
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MikroISPTheme {
                MikroIspApp()
            }
        }
    }

    @Composable
    fun MikroIspApp() {
        val mainViewModel = hiltViewModel<ClientsViewModel>()
        val clientsState by rememberFlowWithLifecycle(mainViewModel.clientsState)
            .collectAsState(initial = ClientUiState.Empty)

        Scaffold(
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(items = clientsState.data) { item ->
                    ClientWidget(
                        item = item,
                        itemClickListener = {

                        },
                        paddingValues = PaddingValues(start = 30.dp, end = 30.dp)
                    )

                    LDivider(
                        paddingValues = PaddingValues(
                            start = 30.dp,
                            end = 30.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                    )
                }
            }
        }
        LaunchedEffect(Unit) {
            mainViewModel.getClients(1)
        }
    }

}








