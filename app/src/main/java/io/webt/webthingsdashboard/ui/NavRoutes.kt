package io.webt.webthingsdashboard.ui

sealed class NavRoutes(val route: String) {
    object HomeScreen : NavRoutes("home")
    object GatewaySetting: NavRoutes("gateway_settings")
}