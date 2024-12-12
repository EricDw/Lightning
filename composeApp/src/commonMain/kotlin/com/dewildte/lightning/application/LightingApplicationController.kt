package com.dewildte.lightning.application

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
rt com.dewildte.lightning.feature.transactions.TransactionsRoute
import com.dewildte.lightning.feature.transactions.transactionsGraph


@Composable
fun LightningApplicationController(
    model: LightningApplication
) {

    val navController = rememberNavController()

    var selectedDestination by remember {
        mutableStateOf<AppDestination?>(null)
    }

    LightningScaffold(
        selectedDestination = AppDestination.TRANSACTIONS,
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
            startDestination = TransactionsRoute,
            modifier = Modifier.fillMaxSize(),
        ) {

            transactionsGraph(model = model)

        }

    }

}