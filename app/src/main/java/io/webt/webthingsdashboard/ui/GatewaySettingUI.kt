package io.webt.webthingsdashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun GatewaySettings(navController: NavController){
    val roundedCorner = 5.dp
    val itemHeight = 48.dp
    val buttonWidth = 100.dp
    val textFieldWidth = 220.dp


    Surface(modifier = Modifier.fillMaxSize()) {
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
                                value = "", onValueChange = { },
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
                                value = "", onValueChange = { },
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
                                value = "", onValueChange = { },
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
                                checked = true,
                                onCheckedChange = { },
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
                                value = "", onValueChange = { },
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
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .width(buttonWidth)
                            .padding(end = 5.dp)
                    ) {
                        Text(stringResource(id = R.string.save))
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.width(buttonWidth)
                            .padding(start = 5.dp)
                    ) {
                        Text(stringResource(id = R.string.cancel))
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