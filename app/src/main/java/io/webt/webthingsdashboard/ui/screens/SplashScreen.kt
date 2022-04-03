package io.webt.webthingsdashboard.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import io.webt.webthingsdashboard.DEVICES_PREVIEW
import io.webt.webthingsdashboard.R
import io.webt.webthingsdashboard.ui.theme.WebthingsDashboardTheme

/*
 *Splash Screen
 */

@Composable
fun SplashScreen(){
    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize()){
        val image: Painter = painterResource(id = R.drawable.ic_launcher_foreground)
        //TODO use a logo webthings gateway dashboard
        //TODO add loading bar??? or not
        Image(painter = image, contentDescription = "")
    }

}

@Preview(showBackground = true, device = DEVICES_PREVIEW)
@Composable
fun SplashScreenPreview(){
    WebthingsDashboardTheme {
        SplashScreen()
    }
}