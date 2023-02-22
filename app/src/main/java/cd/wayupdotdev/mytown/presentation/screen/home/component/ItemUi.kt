package cd.wayupdotdev.mytown.presentation.screen.home.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import cd.wayupdotdev.mytown.R
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cd.wayupdotdev.mytown.data.model.Post
import cd.wayupdotdev.mytown.ui.theme.Black_camera
import cd.wayupdotdev.mytown.ui.theme.Black_ic
import cd.wayupdotdev.mytown.ui.theme.Purple200
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.delay

@Composable
fun DisplayItShow(
    posts: ArrayList<Post>,
    onAddToFavorite: (Post) -> Unit,
    selectedItem: (Post) -> (Unit)
) {
    var refreshing by remember { mutableStateOf(false) }

    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(2000)
            refreshing = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = {
            refreshing = true
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                items(count = posts.size) {
                    ItemUi(post = posts[it], onAddToFavorite = { onAddToFavorite.invoke(it) }, selectedItem = { post ->
                        selectedItem.invoke(post)
                    })
                }
                
                item { 
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "<--   post end   -->",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.LightGray,
                        fontStyle = FontStyle.Italic
                    )
                    Spacer(modifier = Modifier.padding(22.dp))
                }
            }
        )
    }
}

@Composable
fun ItemUi(
    modifier: Modifier = Modifier,
    post: Post,
    onAddToFavorite: (Post) -> Unit,
    selectedItem: (Post) -> (Unit)
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        border = BorderStroke(0.5.dp, Purple200)
    ) {
        Box(
            modifier = modifier
                .height(220.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {

            GlideImage(
                imageModel = post.imageUrl,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        selectedItem(post)
                    }
            )

            BottomShadow(
                comment = post.description,
                onAddToFavorite = {}
            )
        }
    }
}

@Composable
fun BottomShadow(
    comment: String,
    onAddToFavorite: (/*Post*/) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0x22FFFFFF),
                        Black_ic,
                        Black_camera
                    )
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.TopStart),
            onClick = { onAddToFavorite(/*post*/) }
        ) {
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = "favorite",
                tint = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .size(30.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier
                .padding(8.dp)
                .size(150.dp), contentAlignment = Alignment.BottomStart) {
                Text(
                    text = comment,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                )
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
                    .border(
                        width = 1.dp,
                        color = Purple200,
                        RoundedCornerShape(corner = CornerSize(10.dp))
                    )
                    .background(color = Purple200)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row {
                    Text(
                        text = "Commentaire",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                    
                    Spacer(modifier = Modifier.padding(2.dp))
                    
                    Icon(painter = painterResource(id = R.drawable.ic_arrow_righ), contentDescription = null, tint = Color.White)
                }
            }
        }
    }
}