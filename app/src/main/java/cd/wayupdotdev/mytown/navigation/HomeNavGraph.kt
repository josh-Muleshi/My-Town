package cd.wayupdotdev.mytown.navigation

import cd.wayupdotdev.mytown.destinations.*
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

private val destinationsList = listOf(
    HomeScreenDestination,
    DetailScreenDestination,
    AboutScreenDestination,
    SettingScreenDestination,
    ProfileScreenDestination
)

object HomeNavGraph : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() = destinationsList.associateBy { it.route }

    override val route: String
        get() = "Main_route"

    override val startRoute: Route
        get() = HomeScreenDestination
}