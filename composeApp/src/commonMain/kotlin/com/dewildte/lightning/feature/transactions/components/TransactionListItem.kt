package com.dewildte.lightning.feature.transactions.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dewildte.lightning.feature.transactions.model.Transaction
import com.dewildte.lightning.feature.transactions.sample.sampleNegativeTransaction
import com.dewildte.lightning.feature.transactions.sample.samplePositiveTransaction
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TransactionListItem(
    transaction: Transaction,
    selected: Boolean = false,
    modifier: Modifier = Modifier,
) {

    val amount = transaction.money.value.toDouble()

    val indicator = when {
        amount > 0 -> {
            Icons.Default.ArrowUpward
        }

        else -> {
            Icons.Default.ArrowDownward
        }
    }

    val colors = if (selected) {
        ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    } else {
        ListItemDefaults.colors()
    }

    ListItem(
        modifier = modifier,
        colors = colors,
        leadingContent = {
            Icon(
                imageVector = indicator,
                contentDescription = null,
            )
        },
        headlineContent = {
            Text(
                text = transaction.source.value,
            )
        },
        trailingContent = {
            val text = if (amount > 0) {
                "$$amount"
            } else {
                "-$${-amount}"
            }
            Text(
                text = text,
            )
        },
        supportingContent = {
            Text(
                text = transaction.destination.value,
            )
        },
    )

}

@Preview
@Composable
private fun NegativeTransactionListItemPreview() {
    TransactionListItem(
        transaction = sampleNegativeTransaction(),
    )
}

@Preview
@Composable
private fun PositiveTransactionListItemPreview() {
    TransactionListItem(
        transaction = samplePositiveTransaction(),
    )
}