package io.webt.webthingsdashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.* //gui component(button, etc...)
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.webt.webthingsdashboard.ui.theme.WebthingsDashboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebthingsDashboardTheme {
                // A surface container using the 'background' color from the theme
                MainApp()
            }
        }
    }
}

@Composable
private fun MainApp(){
    /* TODO */
    GatewaySettings()
}

@Composable
private fun GatewaySettings(){
    //TODO change text to xml string
    val roundedCorner = 5.dp
    val itemHeight = 48.dp
    val textWidth = 80.dp
    val buttonWidth = 100.dp

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.padding(4.dp)) {
            item {
                Surface(color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .height(itemHeight),
                    shape = RoundedCornerShape(roundedCorner)) {
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Text(
                            text = "Name",
                            modifier = Modifier
                                .padding(start = 2.dp)
                                .width(textWidth)
                        )
                        TextField(value = "", onValueChange = { },
                            modifier = Modifier.padding(2.dp),
                            shape = RoundedCornerShape(roundedCorner))
                    }
                }
            }
            //TODO: is there a way to use a function to create the item???
            item {
                Surface(color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .height(itemHeight),
                    shape = RoundedCornerShape(roundedCorner)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Address",
                            modifier = Modifier
                                .padding(start = 2.dp)
                                .width(textWidth)
                        )
                        TextField(value = "", onValueChange = { },
                            modifier = Modifier.padding(2.dp),
                            shape = RoundedCornerShape(roundedCorner))
                    }
                }
            }

            item {
                Surface(color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .height(itemHeight),
                    shape = RoundedCornerShape(roundedCorner)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Port",
                            modifier = Modifier
                                .padding(start = 2.dp)
                                .width(textWidth)
                        )
                        TextField(value = "", onValueChange = { },
                            modifier = Modifier.padding(2.dp),
                            shape = RoundedCornerShape(roundedCorner))
                    }
                }
            }

            item {
                Surface(color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .height(itemHeight),
                    shape = RoundedCornerShape(roundedCorner)) {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier) {
                        Text(
                            text = "Enable SSL",
                            modifier = Modifier
                                .padding(start = 2.dp)
                                .width(textWidth)
                        )
                        Switch(checked = true, onCheckedChange = {  },
                            modifier = Modifier.fillMaxWidth())
                    }
                }
            }
            item {
                Surface(color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .height(itemHeight),
                    shape = RoundedCornerShape(roundedCorner)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Token",
                            modifier = Modifier
                                .padding(start = 2.dp)
                                .width(75.dp)
                        )
                        TextField(value = "", onValueChange = { },
                            modifier = Modifier.padding(2.dp),
                            shape = RoundedCornerShape(roundedCorner))
                    }
                }
            }
            
            item(){
                Row (modifier = Modifier.fillMaxWidth()){
                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier.width(buttonWidth)) {
                        Text("Cancel")

                    }
                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier.width(buttonWidth)) {
                        Text("Save")
                    }
                }
            }

        }

    }

}
/*
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GwSettingsPreview(){
    GatewaySettings()
}
*/
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    WebthingsDashboardTheme {
        MainApp()
    }
}

