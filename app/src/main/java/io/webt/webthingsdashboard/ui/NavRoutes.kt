package io.webt.webthingsdashboard.ui

sealed class NavRoutes(val route: String) {
    object SplashScreen : NavRoutes("splash_screen")
    object HomeScreen : NavRoutes("home")
    object GwSettingScreen: NavRoutes("gateway_settings")
}