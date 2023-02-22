package cd.wayupdotdev.mytown.presentation.screen.home.view

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import cd.wayupdotdev.mytown.destinations.DetailScreenDestination
import cd.wayupdotdev.mytown.destinations.SettingScreenDestination
import cd.wayupdotdev.mytown.presentation.screen.home.business.HomeState
import cd.wayupdotdev.mytown.presentation.screen.home.business.HomeViewModel
import cd.wayupdotdev.mytown.presentation.screen.home.component.DisplayItShow
import cd.wayupdotdev.mytown.presentation.screen.home.component.TopPageBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator, viewModel: HomeViewModel = hiltViewModel()) {

    val context = LocalContext.current
    BackHandler {
        (context as? Activity)?.finish()
    }

    val posts by viewModel.data.collectAsState()

    Scaffold(
        topBar = {
            TopPageBar(Icons.Default.Settings){
                navigator.navigate(SettingScreenDestination)
            }
        }
    ) { contentPadding ->
        if (posts is HomeState.Success) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding)
            ) {
                DisplayItShow(
                    (posts as HomeState.Success).posts,
                    onAddToFavorite = {
                        viewModel.addToFavorite(it)
                        Toast.makeText(context, "Ajouté aux favoris", Toast.LENGTH_SHORT).show() },
                    selectedItem = { post ->
                        navigator.navigate(DetailScreenDestination(postUid = post.uid))
                    }
                )
            }
        } else {
            //NoDataScreen()
        }
    }
}