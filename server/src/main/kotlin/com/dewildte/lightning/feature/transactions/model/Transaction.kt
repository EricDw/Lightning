package com.dewildte.lightning.feature.transactions.model

import com.dewildte.lightning.feature.tags.model.Tag

class Transaction(
    val id: TransactionId,
    val source: Source,
    val destination: Destination,
    val date: TransactionDate,
    val note: Note,
    val money: Money,
    val tags: List<Tag>,
)  {
    init {
        // TODO: Implement validation logic
    }
}