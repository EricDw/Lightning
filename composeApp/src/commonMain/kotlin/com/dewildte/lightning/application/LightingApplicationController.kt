package com.dewildte.lightning.application

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dewildte.lightning.application.components.LightningScaffold
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.design.theme.LightningTheme
import com.dewildte.lightning.feature.onboarding.OnboardingRoute
import com.dewildte.lightning.feature.onboarding.onboardingNavigationGraph
import com.dewildte.lightning.feature.transactions.TransactionsRoute
import com.dewildte.lightning.feature.transactions.transactionsGraph
import com.dewildte.lightning.models.users.User

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

    var selectedDestination by remember {
        mutableStateOf<AppDestination?>(null)
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
                        // TODO: Implement
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
                        // TODO: Set up, and navigate to, the actual home screen.
                        selectedDestination = AppDestination.TRANSACTIONS
                        navController.navigate(route = TransactionsRoute)
                    },
                )
                // TODO: Home Graph
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