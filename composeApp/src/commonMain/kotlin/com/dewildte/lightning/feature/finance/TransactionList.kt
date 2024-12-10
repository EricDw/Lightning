package com.dewildte.lightning.feature.finance

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dewildte.lightning.finance.ReadTransaction

@Composable
fun TransactionList(
    modifier: Modifier = Modifier,
    transactions: List<ReadTransaction> = emptyList(),
    onLoadClick: () -> Unit = {}
) {

    if (transactions.isEmpty()) {

        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Button(onClick = onLoadClick) {
                Text(text = "Load Transactions")
            }
        }

    } else {

        LazyColumn(
            modifier = modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            items(
                items = transactions,
                key = { it.id.value },
            ) { transaction ->

                val amount = transaction.amount.value

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
                    border = BorderStroke(width = 1.dp, color = borderColor)
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = transaction.toString(),
                    )
                }
            }
        }
    }
}