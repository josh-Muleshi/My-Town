package cd.wayupdotdev.mytown.presentation.screen.settings.view

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cd.wayupdotdev.mytown.R
import cd.wayupdotdev.mytown.destinations.AboutScreenDestination
import cd.wayupdotdev.mytown.presentation.common.AppBarScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SettingScreen(navigator: DestinationsNavigator) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppBarScreen(navigator, screenName = "Settings")
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {

            SettingItem { item ->
                when (item) {
                    0 -> {
                        Toast.makeText(context, "Seulement le français pour l'instant", Toast.LENGTH_LONG).show()
                    }
                    1 -> { navigator.navigate(AboutScreenDestination) }
                    2 -> {
                        Toast.makeText(context, "indisponible pour l'instant", Toast.LENGTH_LONG).show()
                        /*context.openUrl("https://www.weboxconnexion.com")*/
                    }
                }
            }
        }
    }
}

@Composable
fun SettingItem(selectedItem: (Int) -> Unit) {
    val names: List<String> = listOf("Langue", "Apropos de nous", "Aide")
    LazyColumn {
        itemsIndexed(names) { index, name ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selectedItem(index) },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = name, modifier = Modifier.padding(16.dp), color = Color.Black)
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_righ),
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Divider()
        }
    }
}