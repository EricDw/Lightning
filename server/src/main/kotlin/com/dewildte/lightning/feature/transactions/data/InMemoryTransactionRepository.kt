package com.dewildte.lightning.dtos.transactions.data

import com.dewildte.lightning.models.tags.Tag
import com.dewildte.lightning.models.tags.TagId
import com.dewildte.lightning.models.tags.TagLabel
import com.dewildte.lightning.models.transactions.*
import kotlinx.datetime.LocalDate
import kotlin.uuid.Uuid

class InMemoryTransactionRepository : TransactionRepository {

    private val transactionId1 = TransactionId(value = Uuid.random())

    private val transactionId2 = TransactionId(value = Uuid.random())

    private val tagId1 = TagId(value = Uuid.random())

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
                )
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
                )
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