package com.loogika.mikroisp.dashboard.navigation

import androidx.annotation.DrawableRes
import com.loogika.mikroisp.core.navigation.DashboardScreens
import com.loogika.mikroisp.core.R

sealed class NavigationBottomItems(
    val route: String,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int
) {
    object DashboardHome : NavigationBottomItems(
        DashboardScreens.Home.route,
        R.drawable.ic_dashboard,
        R.drawable.ic_dashboard_active
    )

    object DashboardClient : NavigationBottomItems(
        DashboardScreens.Client.route,
        R.drawable.ic_client,
        R.drawable.ic_client_active
    )

    object DashboardPayment : NavigationBottomItems(
        DashboardScreens.Payment.route,
        R.drawable.ic_receipt,
        R.drawable.ic_receipt_active
    )
}