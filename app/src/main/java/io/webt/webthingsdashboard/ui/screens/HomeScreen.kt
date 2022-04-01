package io.webt.webthingsdashboard.ui.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import io.webt.webthingsdashboard.ui.NavRoutes
import io.webt.webthingsdashboard.R

@Composable
fun HomeScreen(navController: NavController?){
    /* TODO */

    Scaffold(
        topBar = { TopBar(navController = navController!!) },
        content = {

            /* TODO */
        })
}

@Composable
private fun TopBar(navController: NavController){
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            IconButton(onClick = { navController.navigate(NavRoutes.GwSettingScreen.route) }){
            Icon(Icons.Filled.Settings, contentDescription = "",
            tint = MaterialTheme.colors.onBackground)
            }
        }
    )
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun HomeScreenPreview(){
    HomeScreen(null)
}
