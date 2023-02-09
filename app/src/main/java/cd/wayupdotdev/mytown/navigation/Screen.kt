package cd.wayupdotdev.mytown.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import cd.wayupdotdev.mytown.destinations.HomeScreenDestination
import cd.wayupdotdev.mytown.destinations.PostScreenDestination
import cd.wayupdotdev.mytown.destinations.ProfileScreenDestination
import cd.wayupdotdev.mytown.presentation.Screen.post.view.PostScreen
import com.ramcosta.composedestinations.spec.Direction

sealed class Screen(val destination: Direction, val route: String = destination.route, val label: String = "", val icon: ImageVector? = null){
    object Home : Screen(destination = HomeScreenDestination, label = "Accueil", icon = Icons.Default.Home)
    object Post : Screen(destination = PostScreenDestination, label = "Post" , icon = Icons.Default.Add)
    object Profile : Screen(destination = ProfileScreenDestination, label = "Profile",icon = Icons.Default.Person)
}

fun getBottomNavItems(): List<Screen> {
    return listOf(Screen.Home, Screen.Post, Screen.Profile)
}

