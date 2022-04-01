package io.webt.webthingsdashboard.ui

sealed class NavRoutes(val route: String) {
    object HomeScreen : NavRoutes("home")
    object GwSettingScreen: NavRoutes("gateway_settings")
}