package com.dewildte.lightning.feature.transactions.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dewildte.lightning.feature.transactions.model.Transaction
import com.dewildte.lightning.feature.transactions.sample.sampleNegativeTransaction
import com.dewildte.lightning.feature.transactions.sample.samplePositiveTransaction
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TransactionCard(
    transaction: Transaction,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {

    val amount = transaction.money.value

    val borderColor = when {
        amount < 0 -> {
            MaterialTheme.colorScheme.error
        }

        amount > 0 -> {
            Color.Green
        }

        else -> {
            Color.Transparent
        }
    }

    Card(
        modifier = modifier,
        border = BorderStroke(width = 1.dp, color = borderColor),
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = transaction.toString(),
        )
    }

}

@Preview
@Composable
private fun NegativeTransactionCardPreview() {
    TransactionCard(
        transaction = sampleNegativeTransaction
    )
}

@Preview
@Composable
private fun PositiveTransactionCardPreview() {
    TransactionCard(
        transaction = samplePositiveTransaction
    )
}