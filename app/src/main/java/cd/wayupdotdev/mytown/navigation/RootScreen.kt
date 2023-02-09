@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialNavigationApi::class
)

package cd.wayupdotdev.mytown.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import cd.wayupdotdev.mytown.destinations.SplashScreenDestination
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@Destination
@RootNavGraph(start = true)
@Composable
fun SetupNavGraph() {

    val engine = rememberAnimatedNavHostEngine()
    val navController = engine.rememberNavController()

    val startRoute = SplashScreenDestination
    DestinationsNavHost(
        navGraph = StartNavGraph,
        startRoute = startRoute,
        engine = engine,
        navController = navController
    )
}
