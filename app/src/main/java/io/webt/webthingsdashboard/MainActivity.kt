package io.webt.webthingsdashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.webt.webthingsdashboard.ui.theme.WebthingsDashboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebthingsDashboardTheme {
                NavTest()
            }
        }
    }
}

@Composable
fun NavTest(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavRoutes.MainApp.route,
    ){
        composable(NavRoutes.MainApp.route){
            MainApp(navController = navController)
        }
        composable(NavRoutes.GatewaySetting.route){
            GatewaySettings(navController = navController)
        }
    }
}
@Composable
fun MainApp(navController: NavController){
    /* TODO */

    Scaffold(
        topBar = { TopBar(navController = navController) },
        content = {

        /* TODO */
        })
}

@Composable
private fun TopBar(navController: NavController){
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {IconButton(onClick = { navController.navigate(NavRoutes.GatewaySetting.route) }){
            Icon(Icons.Filled.Settings, contentDescription = "" )
            }
        } )
}
/*
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    WebthingsDashboardTheme {
        MainApp()
    }
}
*/
