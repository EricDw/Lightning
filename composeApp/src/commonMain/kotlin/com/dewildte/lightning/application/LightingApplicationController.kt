package com.dewildte.lightning.application

import HomeRoute
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dewildte.lightning.design.components.LightningScaffold
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.design.theme.LightningTheme
import com.dewildte.lightning.feature.onboarding.OnboardingRoute
import com.dewildte.lightning.feature.onboarding.onboardingNavigationGraph
import com.dewildte.lightning.feature.transactions.TransactionsRoute
import com.dewildte.lightning.feature.transactions.transactionsGraph
import com.dewildte.lightning.models.users.User
import homeNavigationGraph

@Composable
fun LightningApplicationController(
    model: LightningApplication
) {

    val viewModel: LightningApplicationViewModel = viewModel {
        LightningApplicationViewModel(
            model = model
        )
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    val navController = rememberNavController()

    var selectedDestination by remember(state.user) {
        val destination = if (state.user == null) {
            null
        } else {
            AppDestination.TRANSACTIONS
        }

        mutableStateOf(destination)
    }

    val startDestination: Any = if (state.user == null) {
        OnboardingRoute
    } else {
        TransactionsRoute
    }

    LightningTheme(
        darkTheme = isSystemInDarkTheme()
    ) {
        LightningScaffold(
            selectedDestination = selectedDestination,
            onDestinationClick = { destination ->
                when (destination) {
                    AppDestination.HOME -> {
                        selectedDestination = destination
                        navController.navigate(route = HomeRoute) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }

                    AppDestination.TRANSACTIONS -> {
                        selectedDestination = destination
                        navController.navigate(route = TransactionsRoute) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }

                    AppDestination.SETTINGS -> {
                        // TODO: Implement
                    }
                }
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier.fillMaxSize(),
            ) {
                onboardingNavigationGraph(
                    model = model,
                    navController = navController,
                    navigateToHome = {
                        selectedDestination = AppDestination.HOME
                        navController.navigate(route = HomeRoute) {
                            popUpTo(route = OnboardingRoute) {
                                inclusive = true
                            }
                        }
                    },
                    onLaunched = {
                        selectedDestination = null
                    },
                )

                homeNavigationGraph(
                    model = model,
                    onLaunched = {
                        selectedDestination = AppDestination.HOME
                    },
                )

                transactionsGraph(
                    model = model,
                    onLaunched = {
                        selectedDestination = AppDestination.TRANSACTIONS
                    },
                )
                // TODO: Settings Graph
            }
        }
    }
}

@Immutable
data class LightingApplicationState(
    val user: User? = null
)