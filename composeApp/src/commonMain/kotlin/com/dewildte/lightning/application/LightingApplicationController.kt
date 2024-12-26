package com.dewildte.lightning.application

import HomeRoute
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
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
                        navController.navigate(route = HomeRoute)
                    }

                    AppDestination.TRANSACTIONS -> {
                        selectedDestination = destination
                        navController.navigate(route = TransactionsRoute)
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
                    navigateToHome = {
                        selectedDestination = AppDestination.HOME
                        navController.navigate(route = HomeRoute)
                    },
                )

                homeNavigationGraph(model = model)

                transactionsGraph(model = model)
                // TODO: Settings Graph
            }
        }
    }

}

@Immutable
data class LightingApplicationState(
    val user: User? = null
)