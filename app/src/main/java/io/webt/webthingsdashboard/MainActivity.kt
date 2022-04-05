package io.webt.webthingsdashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.webt.webthingsdashboard.ui.NavRoutes
import io.webt.webthingsdashboard.ui.screens.AddDashboard
import io.webt.webthingsdashboard.ui.screens.GwSettingScreen
import io.webt.webthingsdashboard.ui.screens.HomeScreen
import io.webt.webthingsdashboard.ui.screens.SplashScreen
import io.webt.webthingsdashboard.ui.theme.WebthingsDashboardTheme
import io.webt.webthingsdashboard.utils.LoadGwSettings

const val DEVICES_PREVIEW = Devices.PIXEL_4_XL
var DATA_LOADED = false
lateinit var gwSettingsDatas: Any
lateinit var gateway: WebtioGateway

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
    val context = LocalContext.current
    if (!DATA_LOADED) {
        gwSettingsDatas = LoadGwSettings(context) as? MutableMap<String , Any> ?: LoadGwSettings(context) as Boolean
        DATA_LOADED = true
    }
    if (gwSettingsDatas != false){
        val settings = gwSettingsDatas as MutableMap<String, Any>
        gateway = WebtioGateway(settings["address"] as String, settings["token"] as String, (settings["port"] as Int).toString(), settings["ssl"] as Boolean, context)
        gateway.initializeThings()
    }
    NavHost(
        navController = navController,
        startDestination = NavRoutes.HomeScreen.route,//NavRoutes.SplashScreen.route,
        //TODO Splash Screen seeam to cause issue on home screen, disabled for now
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
        composable(NavRoutes.AddDashboard.route){
            AddDashboard(navController = navController)
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

