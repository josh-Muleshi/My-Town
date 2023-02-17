package cd.wayupdotdev.mytown.presentation.Screen.post.view

import android.net.Uri
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun PostMakeScreen(uri: Uri) {
    Text(text = "$uri")
}