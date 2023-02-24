package cd.wayupdotdev.mytown.presentation.screen.profile.view

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cd.wayupdotdev.mytown.R
import cd.wayupdotdev.mytown.app.App
import cd.wayupdotdev.mytown.data.model.User
import cd.wayupdotdev.mytown.destinations.AuthScreenDestination
import cd.wayupdotdev.mytown.destinations.DetailScreenDestination
import cd.wayupdotdev.mytown.destinations.SettingScreenDestination
import cd.wayupdotdev.mytown.domain.dto.CustomFirebaseUser
import cd.wayupdotdev.mytown.navigation.SetupNavGraph
import cd.wayupdotdev.mytown.presentation.common.AppBarScreen
import cd.wayupdotdev.mytown.presentation.screen.home.business.HomeState
import cd.wayupdotdev.mytown.presentation.screen.home.component.DisplayItShow
import cd.wayupdotdev.mytown.presentation.screen.profile.business.ProfileState
import cd.wayupdotdev.mytown.presentation.screen.profile.business.ProfileViewModel
import cd.wayupdotdev.mytown.presentation.screen.profile.component.ProfileAppBar
import cd.wayupdotdev.mytown.ui.theme.Purple200
import cd.wayupdotdev.mytown.utils.Constants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
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