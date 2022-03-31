package io.webt.webthingsdashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.webt.webthingsdashboard.ui.NavRoutes
import io.webt.webthingsdashboard.ui.screens.HomeScreen
import io.webt.webthingsdashboard.ui.theme.WebthingsDashboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebthingsDashboardTheme {
                NavApp()
            }
        }
    }
}

@Composable
fun NavApp(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavRoutes.HomeScreen.route,
    ){
        composable(NavRoutes.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(NavRoutes.GatewaySetting.route){
            GatewaySettings(navController = navController)
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    WebthingsDashboardTheme {
        NavApp()
    }
}

