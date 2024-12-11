package com.dewildte.lightning.feature.transactions.sample

import com.dewildte.lightning.finance.MoneyDTO
import com.dewildte.lightning.finance.TransactionDTO
import com.dewildte.lightning.finance.IdentifierDTO
import com.dewildte.lightning.finance.TagDTO

val sampleNegativeTransaction = TransactionDTO(
    id = IdentifierDTO(value = "0"),
    source = "VISA",
    destination = "Big Box Store",
    note = "Bought chairs",
    moneyDTO = MoneyDTO(value = -5),
    tags = listOf(
        TagDTO(
            identifierDTO = IdentifierDTO("0"),
            label = "Household"
        )
    )
)

val samplePositiveTransaction = TransactionDTO(
    id = IdentifierDTO(value = "1"),
    source = "Big Box Store",
    destination = "VISA",
    note = "Returned chairs",
    moneyDTO = MoneyDTO(value = 5),
    tags = listOf(
        TagDTO(
            identifierDTO = IdentifierDTO("0"),
            label = "Household"
        )
    ),
)

val sampleTransactonList = listOf(
    sampleNegativeTransaction,
    samplePositiveTransaction,
)