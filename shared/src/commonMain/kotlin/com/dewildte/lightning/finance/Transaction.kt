package com.dewildte.lightning.finance

import kotlinx.serialization.Serializable

@Serializable
data class TransactionId(
    val value: String,
)

@Serializable
data class ReadTransaction(
    val id: TransactionId,
    val source: String,
    val destination: String,
    val note: String,
    val amount: Money,
)

@Serializable
data class WriteTransaction(
    val id: TransactionId,
    val source: String,
    val destination: String,
    val note: String,
    val amount: Money,
)