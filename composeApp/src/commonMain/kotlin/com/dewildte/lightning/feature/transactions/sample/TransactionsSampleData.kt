package com.dewildte.lightning.feature.transactions.sample

import com.dewildte.lightning.feature.transactions.data.MoneyDTO
import com.dewildte.lightning.feature.transactions.data.TransactionDTO
import com.dewildte.lightning.feature.transactions.data.TransactionIdDTO
import com.dewildte.lightning.feature.tags.data.TagDTO

val sampleNegativeTransaction = TransactionDTO(
    id = TransactionIdDTO(value = "0"),
    source = "VISA",
    destination = "Big Box Store",
    note = "Bought chairs",
    money = MoneyDTO(value = -5),
    tags = listOf(
        TagDTO(
            id = TransactionIdDTO("0"),
            label = "Household"
        )
    )
)

val samplePositiveTransaction = TransactionDTO(
    id = TransactionIdDTO(value = "1"),
    source = "Big Box Store",
    destination = "VISA",
    note = "Returned chairs",
    money = MoneyDTO(value = 5),
    tags = listOf(
        TagDTO(
            id = TransactionIdDTO("0"),
            label = "Household"
        )
    ),
)

val sampleTransactonList = listOf(
    sampleNegativeTransaction,
    samplePositiveTransaction,
)