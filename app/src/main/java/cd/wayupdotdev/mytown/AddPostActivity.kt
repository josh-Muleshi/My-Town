package cd.wayupdotdev.mytown

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cd.wayupdotdev.mytown.navigation.AddPostScreen
import cd.wayupdotdev.mytown.ui.theme.MyTownTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTownTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AddPostScreen()
                }
            }
        }
    }
}