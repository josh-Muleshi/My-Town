package cd.wayupdotdev.mytown.presentation.Screen.post.view

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import cd.wayupdotdev.mytown.presentation.Screen.post.business.PostViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalPermissionsApi::class)
@Destination
@Composable
fun PostScreen( viewModel: PostViewModel = hiltViewModel()) {
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
    val screeHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    var previewView: PreviewView


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // we will show camera preview once permission is granted
        if (permissionState.allPermissionsGranted){
            Box(modifier = Modifier
                .height(screeHeight * 0.80f)
                .width(screenWidth)) {
                AndroidView(
                    factory = {
                        previewView = PreviewView(it)
                        viewModel.showCameraPreview(previewView, lifecycleOwner)
                        previewView
                    },
                    modifier = Modifier
                        .height(screeHeight * 0.80f)
                        .width(screenWidth)
                )
            }
        }

        Box(
            modifier = Modifier
                .height(screeHeight*0.80f),
            contentAlignment = Alignment.TopCenter
        ){
            IconButton(modifier = Modifier.padding(16.dp),onClick = {
                if (permissionState.allPermissionsGranted){
                    viewModel.captureAndSave(context)
                }
                else{
                    Toast.makeText(
                        context,
                        "Please accept permission in app settings",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }) {

                Icon(imageVector = Icons.Default.Phone,
                    contentDescription = "",
                    modifier = Modifier.size(45.dp),
                    tint = Color.Magenta
                )
            }
        }
    }
}