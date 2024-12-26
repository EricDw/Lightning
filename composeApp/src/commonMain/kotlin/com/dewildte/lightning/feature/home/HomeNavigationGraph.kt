import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.feature.home.HomeScreenController
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data object HomeScreenRoute

fun NavGraphBuilder.homeNavigationGraph(
    model: LightningApplication,
) {
    navigation<HomeRoute>(
        startDestination = HomeScreenRoute
    ) {
        composable<HomeScreenRoute>() { navBackStackEntry ->
            HomeScreenController(model = model)
        }
    }
}