package cd.wayupdotdev.mytown.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cd.wayupdotdev.mytown.destinations.HomeScreenDestination
import cd.wayupdotdev.mytown.destinations.PostScreenDestination
import cd.wayupdotdev.mytown.ui.theme.Purple200
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate

@Destination
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val startRoute = HomeScreenDestination

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
          FloatingActionButton(
              shape = RoundedCornerShape(10.dp),
              onClick = {
                  navController.navigate(
                      PostScreenDestination
                  )
              },
              backgroundColor = Purple200
          ) {
              Icon(imageVector = Icons.Default.Add, contentDescription = "add")
          }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        drawerElevation = 0.dp,
        bottomBar = {
            BottomAppBar(backgroundColor = MaterialTheme.colors.surface, elevation = 0.dp) {
                BottomNavigation(backgroundColor = MaterialTheme.colors.surface, elevation = 0.dp) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    getBottomNavItems().forEach { screen ->
                        BottomNavigationItem(
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = { navController.navigate(screen.destination) },
                            selectedContentColor = MaterialTheme.colors.primary,
                            unselectedContentColor = Color.Gray,
                            icon = {
                                Icon(
                                    imageVector = screen.icon as ImageVector,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        },
        content = { contentPadding ->
            DestinationsNavHost(
                navGraph = HomeNavGraph,
                startRoute = startRoute,
                navController = navController,
                modifier = Modifier.padding(contentPadding)
            )
        }
    )
}