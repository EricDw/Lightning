package com.dewildte.lightning.feature.transactions

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.dewildte.lightning.application.api.LightningApplication
import kotlinx.serialization.Serializable

@Serializable
data object TransactionsRoute

@Serializable
internal data object TransactionsScreenRoute

fun NavGraphBuilder.transactionsGraph(
    model: LightningApplication
) {

    navigation<TransactionsRoute>(
        startDestination = TransactionsScreenRoute
    ) {

        composable<TransactionsScreenRoute>() {

            TransactionsScreenController(
                viewModel = viewModel { TransactionsScreenViewModel(model = model) },
                navigateToTransactionDetails = { transaction ->
                    TODO("Not yet implemented")
                }
            )

        }

    }

}