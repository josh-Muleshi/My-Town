package cd.wayupdotdev.mytown.presentation.screen.profile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cd.wayupdotdev.mytown.R
import cd.wayupdotdev.mytown.destinations.SettingScreenDestination
import cd.wayupdotdev.mytown.presentation.common.AppBarScreen
import cd.wayupdotdev.mytown.presentation.screen.profile.component.ProfileAppBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ProfileScreen(navigator : DestinationsNavigator) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        modifier = Modifier.background(Color.LightGray),
        topBar = {
            ProfileAppBar(
                backButton = { navigator.navigateUp() },
                settingScreen = { navigator.navigate(SettingScreenDestination) }
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.padding(8.dp))
            }

            item {
                Image(
                    painter = painterResource(id = R.drawable.logo_two),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(0.dp, MaterialTheme.colors.surface, CircleShape),
                    contentScale = ContentScale.Crop,
                )
            }

            item {
                Spacer(modifier = Modifier.padding(8.dp))
            }

            item {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}