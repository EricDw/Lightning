package com.dewildte.lightning.models.transactions

import androidx.compose.runtime.Immutable
import kotlin.uuid.Uuid

@Immutable
data class TransactionId(
    val value: String
)