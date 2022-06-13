package com.loogika.mikroisp.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.loogika.mikroisp.core.navigation.DashboardScreens
import com.loogika.mikroisp.dashboard.screen.DashboardScreen
import com.loogika.mikroisp.uikit.style.MikroISPTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class DashboardActivity : ComponentActivity() {
    private var startDestination = DashboardScreens.Home.route
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MikroISPTheme {
                MikroIspApp()
            }
        }
    }

    @OptIn(ExperimentalMaterialNavigationApi::class)
    @Composable
    fun MikroIspApp() {
        val bottomSheetNavigator = rememberBottomSheetNavigator()
        val navController = rememberNavController(bottomSheetNavigator)

        ModalBottomSheetLayout(bottomSheetNavigator) {
            NavHost(
                navController,
                startDestination = DashboardScreens.Dashboard.route
            ) {
                composable(
                    DashboardScreens.Dashboard.route,
                ) {
                    DashboardScreen(
                        startDestination  = startDestination,
                    )
                }

            }
        }
    }

}








