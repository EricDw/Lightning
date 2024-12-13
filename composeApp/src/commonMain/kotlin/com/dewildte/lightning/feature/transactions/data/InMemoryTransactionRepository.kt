package com.dewildte.lightning.feature.transactions.data

import com.dewildte.lightning.feature.tags.model.Tag
import com.dewildte.lightning.feature.tags.model.TagId
import com.dewildte.lightning.feature.tags.model.TagLabel
import com.dewildte.lightning.feature.transactions.model.*
import kotlinx.datetime.LocalDate
import kotlin.uuid.Uuid

class InMemoryTransactionRepository : TransactionRepository {

    private val transactionId1 = TransactionId(value = "0")
    private val transactionId2 = TransactionId(value = "1")

    private val tagId1 = TagId(value = "0")

    private val _transactions = mutableMapOf(
        transactionId1 to Transaction(
            id = transactionId1,
            source = Source("VISA"),
            destination = Destination("Big Box Store"),
            date = TransactionDate(
                value = LocalDate(
                    year = 2024,
                    monthNumber = 12,
                    dayOfMonth = 31
                ).toString()
            ),
            note = Note("Bought chairs"),
            money = Money(value = -5),
            tags = listOf(
                Tag(
                    id = tagId1,
                    label = TagLabel("Household")
                )
            )
        ),
        transactionId2 to Transaction(
            id = transactionId2,
            source = Source("Big Box Store"),
            destination = Destination("VISA"),
            date = TransactionDate(
                value = LocalDate(
                    year = 2025,
                    monthNumber = 1,
                    dayOfMonth = 1
                ).toString()
            ),
            note = Note("Returned chairs"),
            money = Money(value = 5),
            tags = listOf(
                Tag(
                    id = tagId1,
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