package com.loogika.mikroisp.dashboard.widget

import androidx.compose.foundation.Image
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.loogika.mikroisp.dashboard.extensions.changeTab
import com.loogika.mikroisp.dashboard.navigation.NavigationBottomItems

@Composable
fun DashboardBottomNavigationWidget(
    navigationItems: List<NavigationBottomItems>,
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 30.dp,
    ) {
        navigationItems.forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                icon = {
                    Image(
                        painter = painterResource(
                            id = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) screen.selectedIcon else screen.icon
                        ),
                        contentDescription = null,
                        colorFilter = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true)
                            ColorFilter.tint(MaterialTheme.colors.primary) else { ColorFilter.tint(MaterialTheme.colors.secondary)
                        }
                    )
                },
                selectedContentColor = MaterialTheme.colors.primary,
                onClick = {
                    navController.changeTab(screen.route)
                }
            )
        }
    }
}