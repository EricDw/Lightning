package com.dewildte.lightning.feature.transactions.sample

import com.dewildte.lightning.feature.tags.model.Tag
import com.dewildte.lightning.feature.tags.model.TagId
import com.dewildte.lightning.feature.tags.model.TagLabel
import com.dewildte.lightning.feature.transactions.model.*
import kotlinx.datetime.LocalDate
import kotlin.uuid.Uuid

private val tagId1 = TagId(value = "0")

val sampleNegativeTransaction = Transaction(
    id = TransactionId(value = "0"),
    source = Source(value = "VISA"),
    destination = Destination(value = "Big Box Store"),
    date = TransactionDate(
        value = "2024-12-31"
    ),
    note = Note(value = "Bought chairs"),
    money = Money(value = -5),
    tags = listOf(
        Tag(
            id = tagId1,
            label = TagLabel(value = "Household")
        )
    )
)

val samplePositiveTransaction = Transaction(
    id = TransactionId(value = "1"),
    source = Source(value = "Big Box Store"),
    destination = Destination(value = "VISA"),
    date = TransactionDate(
        value = "2025-1-1",
    ),
    note = Note(value = "Returned chairs"),
    money = Money(value = 5),
    tags = listOf(
        Tag(
            id = tagId1,
            label = TagLabel(value = "Household")
        )
    ),
)

val sampleTransactonList = listOf(
    sampleNegativeTransaction,
    samplePositiveTransaction,
)