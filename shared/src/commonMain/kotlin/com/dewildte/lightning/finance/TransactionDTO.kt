package com.dewildte.lightning.finance

import kotlinx.serialization.Serializable

@Serializable
data class TransactionDTO(
    val id: IdentifierDTO,
    val source: String,
    val destination: String,
    val note: String,
    val moneyDTO: MoneyDTO,
    val tags: List<TagDTO>,
)

