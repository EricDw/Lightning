package com.dewildte.lightning.feature.transactions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dewildte.lightning.design.components.LargePanel
import com.dewildte.lightning.feature.transactions.components.TransactionCard
import com.dewildte.lightning.feature.transactions.sample.sampleTransactonList
import com.dewildte.lightning.feature.transactions.data.TransactionDTO
import lightning.composeapp.generated.resources.Res
import lightning.composeapp.generated.resources.message_loading_transactions
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TransactionsScreenController(
    viewModel: TransactionsScreenViewModel,
    navigateToTransactionDetails: (transaction: TransactionDTO) -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    with(state) {
        TransactionsScreen(
            isLoading = isLoading,
            error = error,
            transactions = transactions,
            onTransactionClick = navigateToTransactionDetails,
        )
    }

    LaunchedEffect(viewModel) {
        viewModel.loadTransactions()
    }

}

@Composable
fun TransactionsScreen(
    isLoading: Boolean = true,
    error: Throwable? = null,
    transactions: List<TransactionDTO> = emptyList(),
    onTransactionClick: (transaction: TransactionDTO) -> Unit = {},
) {

    LargePanel(
        modifier = Modifier.padding(
            16.dp
        ),
    ) {

        when {

            error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = error.toString(),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = stringResource(Res.string.message_loading_transactions))
                }
            }

            else -> {

                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(
                        items = transactions,
                        key = { it.id.value },
                    ) { transaction ->
                        TransactionCard(
                            transaction,
                            onClick = { onTransactionClick(transaction) },
                        )
                    }
                }
            }
        }
    }
}

@Immutable
data class TransactionsScreenState(
    val isLoading: Boolean = true,
    val error: Throwable? = null,
    val transactions: List<TransactionDTO> = emptyList(),
)

@Preview
@Composable
private fun TransactionsScreenPreview() {
    val transactions = remember {
        sampleTransactonList
    }
    TransactionsScreen(
        transactions = transactions,
        isLoading = false,
    )
}