package io.webt.webthingsdashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import io.webt.webthingsdashboard.ui.theme.WebthingsDashboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebthingsDashboardTheme {
                MainApp()
            }
        }
    }
}

@Composable
private fun MainApp(){
    /* TODO */
    var goGatewaySetting by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        topBar = { TopBar( onGatewaySettingsClicked = { goGatewaySetting = true }) },
        content = {
            if(goGatewaySetting) {
                GatewaySettings()
            }else{
                /* TODO */
            }
        })
}

@Composable
private fun TopBar(onGatewaySettingsClicked: () -> Unit){
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {IconButton(onClick = { onGatewaySettingsClicked }){
            Icon(Icons.Filled.Settings, contentDescription = "" )
            }
        } )
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    WebthingsDashboardTheme {
        MainApp()
    }
}


