package io.webt.webthingsdashboard.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.webt.webthingsdashboard.DEVICES_PREVIEW
import io.webt.webthingsdashboard.ui.NavRoutes
import io.webt.webthingsdashboard.R
import io.webt.webthingsdashboard.ui.theme.WebthingsDashboardTheme

@Composable
fun HomeScreen(navController: NavController?){
    Scaffold(
        topBar = { TopBar(navController = navController!!) },
        floatingActionButton = { FloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Add, "")
        }},
        content = {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize().padding(4.dp)) {
                Text(stringResource(id = R.string.no_dashboard))
            }
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

@Preview(showBackground = true, device = DEVICES_PREVIEW)
@Composable
fun HomeScreenPreview(){
    WebthingsDashboardTheme() {
        HomeScreen(rememberNavController())
    }
}
