package com.dewildte.lightning.feature.transactions.model

import androidx.compose.runtime.Immutable
import com.dewildte.lightning.feature.tags.model.Tag

@Immutable
data class Transaction(
    val id: TransactionId,
    val source: Source,
    val destination: Destination,
    val date: TransactionDate,
    val note: Note,
    val money: Money,
    val tags: List<Tag>,
)