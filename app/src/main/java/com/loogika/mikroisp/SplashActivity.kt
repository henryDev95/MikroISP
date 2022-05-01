package com.loogika.mikroisp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.loogika.mikroisp.core.navigation.Screens
import com.loogika.mikroisp.core.navigation.compose.NavigatorEffect
import com.loogika.mikroisp.screen.SplashScreen
import com.loogika.mikroisp.login.ui.screen.LoginScreen
import com.loogika.mikroisp.uikit.style.MikroISPTheme
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterialNavigationApi
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
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
        val bottomSheetNavigator = rememberBottomSheetNavigator()
        val navController = rememberAnimatedNavController(bottomSheetNavigator)

        ModalBottomSheetLayout(bottomSheetNavigator) {
            AnimatedNavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                composable(Screens.SplashScreen.route) {
                    SplashScreen()
                }
                composable(
                    Screens.LoginScreen.route,
                    enterTransition = {
                        slideInHorizontally(initialOffsetX = { 1000 })
                    },
                    exitTransition = {
                        slideOutHorizontally(targetOffsetX = { 1000 })
                    },
                ) {
                    LoginScreen()
                }
            }
        }

        NavigatorEffect { parameters ->
            when (parameters.screen) {
                is Screens.LoginScreen ->
                {
                    navController.navigate(Screens.LoginScreen.route) {
                        popUpTo(Screens.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

}








