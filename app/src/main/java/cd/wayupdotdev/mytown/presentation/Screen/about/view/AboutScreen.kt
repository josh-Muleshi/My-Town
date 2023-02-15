package cd.wayupdotdev.mytown.presentation.Screen.about.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cd.wayupdotdev.mytown.R
import cd.wayupdotdev.mytown.presentation.common.AppBarScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AboutScreen(navigator: DestinationsNavigator) {

    Scaffold(
        topBar = {
            AppBarScreen(navigator, ScreenName = "About us")
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(16.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_town),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(0.dp, MaterialTheme.colors.surface, CircleShape)
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = stringResource(id = R.string.app_name),
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(id = R.string.version),
                modifier = Modifier.padding(4.dp),
                color = Color.Black
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = stringResource(id = R.string.developed),
                modifier = Modifier.padding(4.dp),
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
            )

            Text(
                text = stringResource(id = R.string.developer),
                modifier = Modifier.padding(4.dp),
                color = Color.Black,
                fontSize = 20.sp,
            )

            Text(
                text = stringResource(id = R.string.email),
                modifier = Modifier.padding(4.dp),
                color = Color.Black,
                fontStyle = FontStyle.Italic
            )
        }
    }
}
