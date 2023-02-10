package cd.wayupdotdev.mytown.presentation.Screen.splash.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import cd.wayupdotdev.mytown.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cd.wayupdotdev.mytown.destinations.MainScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@Destination
@Composable
fun SplashScreen(navigator: DestinationsNavigator) {

    LaunchedEffect(key1 = true){
        delay(5000)
        navigator.navigate(MainScreenDestination)
    }

    Scaffold(modifier = Modifier.background(Color.White)) { contentPadding ->
        SplashScreenContent(modifier = Modifier.padding(contentPadding))
    }
}

@Composable
fun SplashScreenContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_town),
                contentDescription = "logo",
                modifier = Modifier
                    .size(150.dp)
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Text(text = stringResource(id = R.string.app_name))
        }

        Image(
            painter = painterResource(id = R.drawable.ic_banner),
            contentDescription = "Splash banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
fun SplashScreenContentView() {
    SplashScreenContent()
}