package cd.wayupdotdev.mytown.presentation.screen.post.view

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import cd.wayupdotdev.mytown.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import cd.wayupdotdev.mytown.destinations.MainScreenDestination
import cd.wayupdotdev.mytown.destinations.PostMakeScreenDestination
import cd.wayupdotdev.mytown.presentation.screen.post.business.PostState
import cd.wayupdotdev.mytown.presentation.screen.post.business.PostViewModel
import cd.wayupdotdev.mytown.ui.theme.Black_camera
import cd.wayupdotdev.mytown.ui.theme.Black_ic
import cd.wayupdotdev.mytown.ui.theme.Purple200
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalPermissionsApi::class)
@Destination
@Composable
fun PostScreen(navigator: DestinationsNavigator ,viewModel: PostViewModel = hiltViewModel()) {

    val data by viewModel.data.collectAsState()
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val permissions = if (Build.VERSION.SDK_INT <= 28){
        listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }else listOf(Manifest.permission.CAMERA)

    val permissionState = rememberMultiplePermissionsState(
        permissions = permissions)

    if (!permissionState.allPermissionsGranted){
        SideEffect {
            permissionState.launchMultiplePermissionRequest()
        }
    }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screeHeight = configuration.screenHeightDp.dp
    var previewView: PreviewView

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    if (imageUri != null) navigator.navigate(PostMakeScreenDestination(uri = imageUri!!))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (permissionState.allPermissionsGranted){
            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                AndroidView(
                    factory = {
                        previewView = PreviewView(it)
                        viewModel.showCameraPreview(previewView, lifecycleOwner)
                        previewView
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )

                IconButton(onClick = {
                    navigator.navigate(MainScreenDestination)
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "back",
                        modifier = Modifier.size(30.dp),
                        tint = Color.White
                    )
                }

                Box(
                    modifier = Modifier
                        .width(screenWidth)
                        .height(screeHeight * 0.20f)
                        .background(Black_ic)
                        .align(Alignment.BottomCenter)
                ){
                    IconButton(
                        modifier = Modifier
                            .padding(start = 45.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterStart)
                            .size(55.dp)
                            .background(Black_camera),
                        onClick = {
                            launcher.launch("image/*")
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.photo_camera),
                            contentDescription = "back",
                            modifier = Modifier.size(30.dp),
                            tint = Color.White
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .align(Alignment.Center)
                            .size(67.dp)
                            .border(2.dp, Purple200, CircleShape),
                    )

                    IconButton(
                        modifier = Modifier
                            .clip(CircleShape)
                            .align(Alignment.Center)
                            .size(60.dp)
                            .background(Purple200),
                        onClick = {
                            viewModel.captureAndSave(context)
                            if (data is PostState.Success) {
                                //navigator.navigate(PostMakeScreenDestination(uri = (data as PostState.Success).uri))
                            }
                        }
                    ){}
                }
            }
        }
    }
}