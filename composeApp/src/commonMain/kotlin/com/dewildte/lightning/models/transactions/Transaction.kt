package com.dewildte.lightning.models.transactions

import androidx.compose.runtime.Immutable
import com.dewildte.lightning.models.tags.Tag

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