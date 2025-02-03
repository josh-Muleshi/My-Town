package cd.wayupdotdev.mytown.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import cd.wayupdotdev.mytown.destinations.HomeScreenDestination
import cd.wayupdotdev.mytown.destinations.PostScreenDestination
import cd.wayupdotdev.mytown.destinations.ProfileScreenDestination

sealed class Screen(
    val destination: String,
    val label: String = "",
    val icon: ImageVector? = null
){
    data object Home : Screen(destination = HomeScreenDestination.route, label = "home_screen", icon = Icons.Default.Home)
    data object Post : Screen(destination = PostScreenDestination.route, label = "post_screen" , icon = Icons.Default.Add)
    data object Profile : Screen(
        destination = ProfileScreenDestination.route, label = "profile_screen",
        icon = Icons.Default.Person)
}

fun getBottomNavItems(): List<Screen> {
    return listOf(Screen.Home, Screen.Profile)
}

