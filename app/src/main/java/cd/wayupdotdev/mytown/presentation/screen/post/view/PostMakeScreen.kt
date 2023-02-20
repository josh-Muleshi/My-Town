package cd.wayupdotdev.mytown.presentation.screen.post.view

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cd.wayupdotdev.mytown.destinations.MainScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.glide.GlideImage

@Destination
@Composable
fun PostMakeScreen(navigator: DestinationsNavigator, uri: Uri) {

    var comment by remember { mutableStateOf("") }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screeHeight = configuration.screenHeightDp.dp

    LazyColumn {

        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(120.dp)
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navigator.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "close",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Button(
                        enabled = comment.isNotEmpty(),
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
                        onClick = {
//                                viewModel.addPost(
//                                    comment,
//                                    imageUri
//                                )
                            navigator.navigate(MainScreenDestination)
                        }
                    ) {
                        Text(text = "Enregistrer")
                    }
                }
            }
        }

        item {
            Column(modifier = Modifier.fillMaxSize()) {
                TextField(
                    value = comment,
                    onValueChange = { comment = it },
                    placeholder = { Text("Ajoutez un commentaire...") },
                    textStyle = TextStyle(
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Medium
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 0.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background,
                        focusedIndicatorColor = MaterialTheme.colors.background,
                        unfocusedIndicatorColor = MaterialTheme.colors.background
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, end = 24.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    GlideImage(
                        imageModel = uri,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(screenWidth*0.75f)
                            .height(screeHeight*0.45f)
                            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp)))

                    )
                }
            }
        }
    }
}