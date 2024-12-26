package com.dewildte.lightning.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.core.layout.WindowWidthSizeClass
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.design.components.LargeIsland
import com.dewildte.lightning.design.theme.LightningTheme
import com.dewildte.lightning.design.components.TransactionList
import com.dewildte.lightning.design.components.TwoPaneLayout
import com.dewildte.lightning.feature.transactions.sample.sampleTransactonList
import com.dewildte.lightning.models.transactions.Transaction
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import lightning.composeapp.generated.resources.Res
import lightning.composeapp.generated.resources.message_loading
import lightning.composeapp.generated.resources.message_select_transaction
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreenController(
    model: LightningApplication,
) {

    val windowWidthSizeClass = currentWindowAdaptiveInfo()
        .windowSizeClass.windowWidthSizeClass

    val twoPane = when (windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            false
        }

        WindowWidthSizeClass.MEDIUM -> {
            true
        }

        else -> {
            // EXPANDED
            true
        }
    }

    val viewModel: HomeScreenViewModel = viewModel {
        HomeScreenViewModel(model = model)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    with(state) {
        HomeScreen(
            twoPane = twoPane,
            isLoading = isLoading,
        )
    }

}

@Composable
fun HomeScreen(
    twoPane: Boolean = false,
    isLoading: Boolean = false,
    selectedTransaction: Transaction? = null,
    transactions: ImmutableList<Transaction> = persistentListOf(),
) {

    TwoPaneLayout(
        modifier = Modifier.padding(16.dp),
        twoPane = twoPane,
        showSecondaryContent = selectedTransaction != null,
        primaryContent = {
            LargeIsland {
                if (isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(Res.string.message_loading),
                            style = MaterialTheme.typography.displayMedium
                        )
                    }
                } else {
                    TransactionList(
                        modifier = Modifier.fillMaxSize().padding(8.dp),
                        selectedTransaction = selectedTransaction,
                        transactions = transactions,
                    )
                }
            }
        },
        secondaryContent = {
            LargeIsland {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    if (selectedTransaction != null) {
                        Text(
                            text = selectedTransaction.toString(),
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        Text(
                            text = stringResource(Res.string.message_select_transaction),
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                }
            }
        },
    )
}

@Immutable
data class HomeScreenState(
    val isLoading: Boolean = true,
)

@Preview
@Composable
private fun HomeScreenPreview() {
    val transactions = remember {
        sampleTransactonList()
    }

    LightningTheme {
        HomeScreen(
            twoPane = false,
            isLoading = false,
            transactions = transactions,
        )
    }
}

@Preview
@Composable
private fun LoadingHomeScreenPreview() {
    LightningTheme {
        HomeScreen(
            isLoading = true
        )
    }
}

@Preview
@Composable
private fun SelectedTransactionHomeScreenPreview() {
    val transactions = remember {
        sampleTransactonList()
    }

    LightningTheme {
        HomeScreen(
            isLoading = false,
            selectedTransaction = transactions.first(),
            transactions = transactions,
        )
    }
}
