package com.dewildte.lightning.models.transactions

import com.dewildte.lightning.models.tags.Tag

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