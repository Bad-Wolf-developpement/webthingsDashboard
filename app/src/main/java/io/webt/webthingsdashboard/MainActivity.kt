package io.webt.webthingsdashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.webt.webthingsdashboard.ui.NavRoutes
import io.webt.webthingsdashboard.ui.screens.GwSettingScreen
import io.webt.webthingsdashboard.ui.screens.HomeScreen
import io.webt.webthingsdashboard.ui.screens.SplashScreen
import io.webt.webthingsdashboard.ui.theme.WebthingsDashboardTheme

const val DEVICES_PREVIEW = Devices.PIXEL_4_XL

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
        startDestination = NavRoutes.SplashScreen.route,
    ){
        composable(NavRoutes.SplashScreen.route){
            SplashScreen(navController = navController)
        }
        composable(NavRoutes.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(NavRoutes.GwSettingScreen.route){
            GwSettingScreen(navController = navController)
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

