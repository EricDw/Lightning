package com.dewildte.lightning.feature.transactions.sample

import com.dewildte.lightning.feature.tags.model.Tag
import com.dewildte.lightning.feature.tags.model.TagId
import com.dewildte.lightning.feature.tags.model.TagLabel
import com.dewildte.lightning.feature.transactions.model.*
import kotlinx.datetime.LocalDate
import kotlin.uuid.Uuid

private fun tagId1(): TagId {
    return TagId(value ="550e8400-e29b-41d4-a716-446655440000")
}

fun sampleNegativeTransaction(): Transaction {
    return Transaction(
        id = TransactionId(value = "550e8400-e29b-41d4-a716-446655440001"),
        source = Source(value = "VISA"),
        destination = Destination(value = "Big Box Store"),
        date = TransactionDate(
            value = LocalDate(
                year = 2024,
                monthNumber = 12,
                dayOfMonth = 31
            )
        ),
        note = Note(value = "Bought chairs"),
        money = Money(value = -5),
        tags = listOf(
            Tag(
                id = tagId1(),
                label = TagLabel(value = "Household")
            )
        )
    )
}

fun samplePositiveTransaction(): Transaction {
    return Transaction(
        id = TransactionId(value = "550e8400-e29b-41d4-a716-446655440002"),
        source = Source(value = "Big Box Store"),
        destination = Destination(value = "VISA"),
        date = TransactionDate(
            value = LocalDate(
                year = 2025,
                monthNumber = 1,
                dayOfMonth = 1
            ),
        ),
        note = Note(value = "Returned chairs"),
        money = Money(value = 5),
        tags = listOf(
            Tag(
                id = tagId1(),
                label = TagLabel(value = "Household")
            )
        ),
    )
}

fun sampleTransactonList(): List<Transaction> {
    return listOf(
        sampleNegativeTransaction(),
        samplePositiveTransaction(),
    )
}