package cd.wayupdotdev.mytown.presentation.screen.splash.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cd.wayupdotdev.mytown.R
import cd.wayupdotdev.mytown.destinations.AuthScreenDestination
import cd.wayupdotdev.mytown.destinations.MainScreenDestination
import cd.wayupdotdev.mytown.presentation.screen.splash.business.SplashState
import cd.wayupdotdev.mytown.presentation.screen.splash.business.SplashViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@Destination
@Composable
fun SplashScreen(navigator: DestinationsNavigator, viewModel: SplashViewModel = hiltViewModel()) {

    val isAuth by viewModel.isAuth.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true, viewModel){
        delay(5000)
        when(isAuth){
            is SplashState.Success -> {
                if ((isAuth as SplashState.Success).isAuth) {
                    navigator.navigate(MainScreenDestination)
                } else {
                    navigator.navigate(AuthScreenDestination)
                }
            }
            is SplashState.Error -> {
                snackbarHostState.showSnackbar((isAuth as SplashState.Error).errorMessage)
            }
            else -> {}
        }
    }

    Scaffold(modifier = Modifier.background(Color.White)) { contentPadding ->
        SplashScreenContent(modifier = Modifier.padding(contentPadding))
    }
}

@Composable
fun SplashScreenContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 200.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_town),
                contentDescription = "logo",
                modifier = Modifier
                    .size(200.dp)
                    .padding(start = 12.dp)
            )
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