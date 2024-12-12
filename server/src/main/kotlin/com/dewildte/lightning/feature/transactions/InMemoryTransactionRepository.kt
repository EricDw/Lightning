package com.dewildte.lightning.feature.transactions

import com.dewildte.lightning.feature.tags.model.Tag
import com.dewildte.lightning.feature.tags.model.TagLabel
import com.dewildte.lightning.feature.transactions.data.TransactionRepository
import com.dewildte.lightning.feature.transactions.model.*

class InMemoryTransactionRepository : TransactionRepository {

    private val _transactions = mutableMapOf(
        TransactionId(value = "0") to Transaction(
            id = TransactionId(value = "0"),
            source = Source("VISA"),
            destination = Destination("Big Box Store"),
            note = Note("Bought chairs"),
            money = Money(value = -5),
            tags = listOf(
                Tag(
                    id = TagId("0"),
                    label = TagLabel("Household")
                )
            )
        ),
        TransactionId(value = "1") to Transaction(
            id = TransactionId(value = "1"),
            source = Source("Big Box Store"),
            destination = Destination("VISA"),
            note = Note("Returned chairs"),
            money = Money(value = 5),
            tags = listOf(
                Tag(
                    id = TagId("0"),
                    label = TagLabel("Household")
                )
            ),
        ),
    )

    override suspend fun retrieveAllTransactions(): List<Transaction> {
        return _transactions.values.toList()
    }

    override suspend fun insertTransaction(transaction: Transaction) {
        _transactions[transaction.id] = transaction
    }

    override suspend fun deleteTransaction(transactionId: TransactionId) {
        _transactions.remove(transactionId)
    }

}