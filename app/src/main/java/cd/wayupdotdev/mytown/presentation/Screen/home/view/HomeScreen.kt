package cd.wayupdotdev.mytown.presentation.Screen.home.view

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import cd.wayupdotdev.mytown.destinations.SettingScreenDestination
import cd.wayupdotdev.mytown.presentation.Screen.home.component.TopPageBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {
    val context = LocalContext.current
    BackHandler {
        (context as? Activity)?.finish()
    }

    //val posts by viewModel.data.collectAsState()

    Scaffold(
        topBar = {
            TopPageBar(Icons.Default.Settings){
                navigator.navigate(SettingScreenDestination)
            }
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding))
    }
}