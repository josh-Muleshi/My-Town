package cd.wayupdotdev.mytown.presentation.screen.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import cd.wayupdotdev.mytown.R
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cd.wayupdotdev.mytown.ui.theme.Black_camera
import cd.wayupdotdev.mytown.ui.theme.Black_ic
import cd.wayupdotdev.mytown.ui.theme.Purple200

@Composable
fun ItemUi(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Box(
            modifier = modifier
                .height(220.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
//        GlideImage(
//            imageModel = post.imageUrl,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(200.dp)
//                .fillMaxWidth()
//                .clickable {
//                    selectedItem(post)
//                }
//        )

            Image(
                painter = painterResource(id = R.drawable.test),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth()
            )

            BottomShadow(
                comment = "j'ajoute un peu plus de text pour voir ce que cela va donner juste pour le fun",
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
                .size(150.dp), contentAlignment = Alignment.BottomCenter) {
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