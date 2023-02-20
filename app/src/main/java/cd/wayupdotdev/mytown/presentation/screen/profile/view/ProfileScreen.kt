package cd.wayupdotdev.mytown.presentation.screen.profile.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cd.wayupdotdev.mytown.presentation.screen.profile.component.ProfileAppBar
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ProfileScreen() {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        modifier = Modifier.background(Color.LightGray),
        topBar = {
            ProfileAppBar()
        }
    ) { contentPadding ->
        Text(text = "Profile screen", modifier = Modifier.padding(contentPadding))
    }
}