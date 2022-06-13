package com.loogika.mikroisp.dashboard.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loogika.mikroisp.dashboard.widget.DashboardBottomNavigationWidget
import com.loogika.mikroisp.client.screen.ClientScreen
import com.loogika.mikroisp.core.navigation.DashboardScreens
import com.loogika.mikroisp.dashboard.navigation.NavigationBottomItems
import com.loogika.mikroisp.home.screen.DashboardHomeScreen

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun DashboardScreen(
    startDestination: String = DashboardScreens.Home.route,
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            DashboardBottomNavigationWidget(
                navigationItems = listOf(
                    NavigationBottomItems.DashboardHome,
                    NavigationBottomItems.DashboardClient,
                    NavigationBottomItems.DashboardPayment,
                ),
                navController = navController
            )
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = startDestination,
            Modifier.padding(innerPadding)
        ) {
            composable(DashboardScreens.Home.route) {
                DashboardHomeScreen()
            }

            composable(DashboardScreens.Client.route) {
                ClientScreen()
            }

            composable(DashboardScreens.Payment.route) {

            }
        }
    }
}