package com.dewildte.lightning.finance

data class TransactionModel(
    val id: IdentifierDTO,
    val source: String,
    val destination: String,
    val note: String,
    val moneyDTO: MoneyDTO,
)