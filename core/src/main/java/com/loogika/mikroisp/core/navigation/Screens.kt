package com.loogika.mikroisp.core.navigation

sealed class Screens(val route: String) {

    object SplashScreen : Screens("SplashScreen")
    object LoginScreen : Screens("LoginScreen")

}