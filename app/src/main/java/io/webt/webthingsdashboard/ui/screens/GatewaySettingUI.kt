package io.webt.webthingsdashboard.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.webt.webthingsdashboard.DEVICES_PREVIEW
import io.webt.webthingsdashboard.R
import io.webt.webthingsdashboard.ui.NavRoutes
import io.webt.webthingsdashboard.ui.theme.WebthingsDashboardTheme
import io.webt.webthingsdashboard.utils.LoadGwSettings
import io.webt.webthingsdashboard.utils.SaveGwSettings
import java.lang.NumberFormatException
import kotlin.properties.Delegates

val backToHome = NavRoutes.HomeScreen.route
@Composable
fun GwSettingScreen(navController: NavController){
    Scaffold(
        topBar = { TopBar(navController = navController) },
        content = {

            GatewaySettings(navController = navController)
        })
}

@Composable
private fun TopBar(navController: NavController){
    val arrowBack = backToHome
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.gwTopBarTitle)) },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(arrowBack) }) {
                Icon(Icons.Filled.ArrowBack, "BackIcon")
            }
        }
        )
}

@Composable
fun GatewaySettings(navController: NavController){
    val roundedCorner = 5.dp
    val itemHeight = 75.dp
    val buttonWidth = 100.dp
    val textFieldWidth = 220.dp
    val cancelClick = backToHome

    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize()) {

        var nameState = rememberSaveable { mutableStateOf("") }
        var addressState = rememberSaveable { mutableStateOf("") }
        var portState = rememberSaveable { mutableStateOf("443")}
        var sslEnabledState = rememberSaveable { mutableStateOf(true) }
        var tokenState = rememberSaveable { mutableStateOf("") }
        val datas = LoadGwSettings(context)

        if (datas != false) {
            val mapDatas = datas as MutableMap<String, Any>
            nameState = rememberSaveable { mutableStateOf(mapDatas["name"] as String) }
            addressState = rememberSaveable { mutableStateOf(mapDatas["address"] as String) }
            portState = rememberSaveable { mutableStateOf((mapDatas["port"] as Int).toString())}
            sslEnabledState = rememberSaveable { mutableStateOf(mapDatas["ssl"] as Boolean) }
            tokenState = rememberSaveable { mutableStateOf(mapDatas["token"] as String) }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LazyColumn(modifier = Modifier
                .padding(4.dp)
                .weight(1f)) {
                item {
                    Surface(
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .height(itemHeight),
                        shape = RoundedCornerShape(roundedCorner)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(id = R.string.gwName),
                                modifier = Modifier
                                    .padding(start = 2.dp)
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                            TextField(
                                value = nameState.value ,
                                onValueChange = { nameState.value = it },
                                modifier = Modifier
                                    .padding(2.dp)
                                    .width(textFieldWidth),
                                shape = RoundedCornerShape(roundedCorner)
                            )
                        }
                    }
                }
                //TODO: is there a way to use a function to create the item???
                item {
                    Surface(
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .height(itemHeight),
                        shape = RoundedCornerShape(roundedCorner)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(id = R.string.gwAddress),
                                modifier = Modifier
                                    .padding(start = 2.dp)
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                            TextField(
                                value = addressState.value,
                                onValueChange = { addressState.value = it },
                                modifier = Modifier
                                    .padding(2.dp)
                                    .width(textFieldWidth),
                                shape = RoundedCornerShape(roundedCorner)
                            )
                        }
                    }
                }

                item {
                    Surface(
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .height(itemHeight),
                        shape = RoundedCornerShape(roundedCorner)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(id = R.string.gwPort),
                                modifier = Modifier
                                    .padding(start = 2.dp)
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                            TextField(
                                value = portState.value,
                                keyboardOptions = KeyboardOptions
                                    .Default
                                    .copy(keyboardType = KeyboardType.Number),
                                onValueChange = { portState.value = it},
                                modifier = Modifier
                                    .padding(2.dp)
                                    .width(textFieldWidth),
                                shape = RoundedCornerShape(roundedCorner)
                            )
                        }
                    }
                }

                item {
                    Surface(
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .height(itemHeight),
                        shape = RoundedCornerShape(roundedCorner)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(id = R.string.gwSsl),
                                modifier = Modifier
                                    .padding(start = 2.dp)
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                            Switch(
                                checked = sslEnabledState.value,
                                onCheckedChange = { sslEnabledState.value = it},
                                modifier = Modifier.padding(end = 5.dp)
                            )
                        }
                    }
                }
                item {
                    Surface(
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .height(itemHeight),
                        shape = RoundedCornerShape(roundedCorner)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(id = R.string.gwToken),
                                modifier = Modifier
                                    .padding(start = 2.dp)
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                            TextField(
                                value = tokenState.value,
                                onValueChange = { tokenState.value = it },
                                modifier = Modifier
                                    .padding(2.dp)
                                    .width(textFieldWidth),
                                shape = RoundedCornerShape(roundedCorner)
                            )
                        }
                    }
                }
            }

            Surface(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
            ) {
                Row(modifier = Modifier.padding(bottom = 10.dp)) {
                    Button(
                        onClick = { saveSettings(nameState.value,
                            addressState.value,
                            portState.value,
                            sslEnabledState.value,
                            tokenState.value,
                            context) },
                        modifier = Modifier
                            .width(buttonWidth)
                            .padding(end = 5.dp)
                    ) {
                        Text(stringResource(id = R.string.save))
                    }
                    Button(
                        onClick = { navController.navigate(cancelClick) },
                        modifier = Modifier
                            .width(buttonWidth)
                            .padding(start = 5.dp)
                    ) {
                        Text(stringResource(id = R.string.cancel))
                    }
                }
            }
        }
    }
}

private fun saveSettings(name : String,
                         address : String,
                         port : String,
                         enableSSL : Boolean,
                         token : String,
                         context: Context){
    var portNb by Delegates.notNull<Int>()
    try{
        portNb = port.toInt()
    } catch (e: NumberFormatException) {
            Toast.makeText(
                context,
                R.string.invalidPort,
                Toast.LENGTH_SHORT
            ).show()
        return
    }

    if ((portNb > 65535) or (portNb < 1)){
        Toast.makeText(
            context,
            R.string.invalidPort,
            Toast.LENGTH_SHORT
        ).show()
        return
    }
    val data = mutableMapOf<String, Any>("name" to name,
        "address" to address,
        "port" to portNb,
        "ssl" to enableSSL,
        "token" to token)
    SaveGwSettings(context, data)
}

@Preview(showBackground = true, device = DEVICES_PREVIEW)
@Composable
fun GwSettingsPreview(){
    WebthingsDashboardTheme() {
    GwSettingScreen(rememberNavController())
    }
}
