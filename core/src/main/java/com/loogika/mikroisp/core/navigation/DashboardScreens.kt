package com.loogika.mikroisp.core.navigation


sealed class DashboardScreens(val route: String) {
    object Home : DashboardScreens("Home")
    object Dashboard : DashboardScreens("Dashboard")
    object Client : DashboardScreens("Client")
    object Payment : DashboardScreens("Payment")
}