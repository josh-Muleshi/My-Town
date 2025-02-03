package cd.wayupdotdev.mytown.presentation.screen.profile.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cd.wayupdotdev.mytown.R
import cd.wayupdotdev.mytown.app.App
import cd.wayupdotdev.mytown.destinations.SettingScreenDestination
import cd.wayupdotdev.mytown.presentation.screen.profile.business.ProfileState
import cd.wayupdotdev.mytown.presentation.screen.profile.business.ProfileViewModel
import cd.wayupdotdev.mytown.presentation.screen.profile.component.ProfileAppBar
import cd.wayupdotdev.mytown.ui.theme.Purple200
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@Destination
@Composable
fun ProfileScreen(navigator : DestinationsNavigator, viewModel: ProfileViewModel = hiltViewModel()) {

    val context = LocalContext.current

    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

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
        if (state is ProfileState.Success) {
            val user = (state as ProfileState.Success).user
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
                    GlideImage(
                        imageModel = user.profileUrl,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(0.dp, MaterialTheme.colors.surface, CircleShape)
                    )
                }

                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                }

                item {
                    Text(
                        text = user.name,
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = user.email,
                        color = Color.LightGray,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                }

                item {
                    OutlinedButton(
                        border = BorderStroke(1.dp, color = Purple200),
                        onClick = {
                            coroutineScope.launch {
                                viewModel.logout()
                                App.restart(context)
                            }

                            //logout = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 32.dp),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = Color.Transparent
                        ),
                        content = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_logout),
                                    tint = Purple200,
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Se connecter avec Google",
                                    style = MaterialTheme.typography.button.copy(color = Purple200)
                                )
                            }
                        }
                    )
                }
            }
        } else {
            //NoDataScreen()
        }
    }
}