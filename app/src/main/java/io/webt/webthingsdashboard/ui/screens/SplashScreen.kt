package io.webt.webthingsdashboard.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.webt.webthingsdashboard.DEVICES_PREVIEW
import io.webt.webthingsdashboard.R
import io.webt.webthingsdashboard.ui.NavRoutes
import io.webt.webthingsdashboard.ui.theme.WebthingsDashboardTheme

/*
 *Splash Screen
 */

@Composable
fun SplashScreen(navController: NavController?){
    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize()){
        val image: Painter = painterResource(id = R.drawable.webthings_gateway_lockup)
        //TODO add a bottom Bad Wolf Developpement Logo
        //TODO add loading bar??? or not
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Image(painter = image,
                contentDescription = "",
                modifier = Modifier
                    .scale(0.85F)
                    .fillMaxWidth()
                    .height(100.dp))
            Text(
                stringResource(id = R.string.dashboard),
                fontSize = 35.sp)
        }
    }
    Thread.sleep(1000)
    navController!!.navigate(NavRoutes.HomeScreen.route)
}

@Preview(showBackground = true, device = DEVICES_PREVIEW)
@Composable
fun SplashScreenPreview(){
    WebthingsDashboardTheme {
        SplashScreen(null)
    }
}