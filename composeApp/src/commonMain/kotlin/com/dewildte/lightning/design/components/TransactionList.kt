package com.dewildte.lightning.design.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.dewildte.lightning.design.theme.LightningTheme
import com.dewildte.lightning.feature.transactions.sample.sampleTransactonList
import com.dewildte.lightning.models.transactions.Transaction
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TransactionList(
    modifier: Modifier = Modifier,
    transactions: ImmutableList<Transaction> = persistentListOf(),
    clipItems: Boolean = true,
    selectedTransaction: Transaction? = null,
    onTransactionClick: (transaction: Transaction) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(
            items = transactions,
            key = { it.id.value },
        ) { transaction ->
            val itemDrawModifier = Modifier
                .let {
                    if (clipItems) {
                        it.clip(MaterialTheme.shapes.medium)
                    } else it
                }
                .clickable(
                    onClick = { onTransactionClick(transaction) },
                    onClickLabel = null,
                    role = Role.Button,
                )
            TransactionListItem(
                transaction = transaction,
                selected = transaction == selectedTransaction,
                modifier = itemDrawModifier,
            )
        }
    }
}

@Preview
@Composable
private fun TransactionListPreview() {
    val transactions = remember {
        sampleTransactonList()
    }

    LightningTheme {
        TransactionList(
            transactions = transactions,
        )
    }
}
