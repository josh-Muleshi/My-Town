@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialNavigationApi::class
)

package cd.wayupdotdev.mytown.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import cd.wayupdotdev.mytown.destinations.PostScreenDestination
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AddPostScreen() {
    val engine = rememberAnimatedNavHostEngine()
    val navController = engine.rememberNavController()

    val startRoute = PostScreenDestination
    DestinationsNavHost(
        navGraph = AddPostNavGraph,
        startRoute = startRoute,
        engine = engine,
        navController = navController
    )
}