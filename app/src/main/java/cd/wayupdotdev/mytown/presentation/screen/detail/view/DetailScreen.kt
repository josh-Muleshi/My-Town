package cd.wayupdotdev.mytown.presentation.screen.detail.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.glide.GlideImage
import cd.wayupdotdev.mytown.data.model.Post
import cd.wayupdotdev.mytown.presentation.screen.detail.business.DetailState
import cd.wayupdotdev.mytown.ui.theme.Black_ic
import cd.wayupdotdev.mytown.presentation.screen.detail.business.DetailViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun DetailScreen(navigator: DestinationsNavigator, postUid: String, viewModel: DetailViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val post by viewModel.data.collectAsState()

    LaunchedEffect(viewModel){
        viewModel.getPostByUid(postUid)
    }

    Scaffold { contentPadding ->
        if (post is DetailState.Success) {
            val customPost = (post as DetailState.Success).post
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding)
            ) {
                DetailScreenContent(
                    customPost,
                    onAddToFavorite = {
                        viewModel.addToFavorite(it)
                        Toast.makeText(context, "Ajouté aux favoris", Toast.LENGTH_SHORT).show()
                    },
                    backButton = { navigator.navigateUp() }
                )
            }
        } else {
            //NoDataScreen()
        }
    }
}

@Composable
fun DetailScreenContent(post: Post, onAddToFavorite: (Post) -> Unit, backButton: () -> Unit) {
    LazyColumn(content = {
        item {
            BannerImageSection(post = post, onAddToFavorite = {onAddToFavorite.invoke(it)}, backButton = backButton)
        }

        item {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = post.description,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    })
}

@Composable
fun BannerImageSection(modifier: Modifier = Modifier, post: Post, onAddToFavorite: (Post) -> Unit, backButton: () -> Unit) {
    Box(
        modifier = modifier
            .height(250.dp)
            .fillMaxWidth()
    ) {
        GlideImage(
            imageModel = post.imageUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
        )

        DetailScreenTopSection(
            post = post,
            onAddToFavorite = { onAddToFavorite.invoke(it) },
            backButton = backButton
        )
    }
}

@Composable
fun DetailScreenTopSection(
    post: Post,
    onAddToFavorite: (Post) -> Unit,
    backButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Black_ic,
                        Color.Transparent
                    )
                )
            )
            .height(70.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .padding(start = 4.dp, end = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = backButton ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back", tint = Color.White)
            }
            Box(
                modifier = Modifier
                    .clickable { onAddToFavorite(post) }
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(color = if (post.isFavorite) Color.Red else Color.White)
                    .padding(start = 8.dp, end = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "favorite", tint = Color.Black)
            }
        }
    }
}
