package io.webt.webthingsdashboard

sealed class NavRoutes(val route: String) {
    object MainApp : NavRoutes("home")
    object GatewaySetting: NavRoutes("gateway_settings")
}