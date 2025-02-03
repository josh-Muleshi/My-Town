package cd.wayupdotdev.mytown.navigation

import cd.wayupdotdev.mytown.destinations.AuthScreenDestination
import cd.wayupdotdev.mytown.destinations.MainScreenDestination
import cd.wayupdotdev.mytown.destinations.ProfileScreenDestination
import cd.wayupdotdev.mytown.destinations.SplashScreenDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

private val destinationsList = listOf(
    SplashScreenDestination,
    AuthScreenDestination,
    MainScreenDestination,
    ProfileScreenDestination
)

object StartNavGraph : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() = destinationsList.associateBy { it.route }

    override val route: String
        get() = "root"

    override val startRoute: Route
        get() = SplashScreenDestination
}