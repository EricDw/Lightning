package com.dewildte.lightning.feature.transactions.model

import androidx.compose.runtime.Immutable
import kotlin.uuid.Uuid

@Immutable
data class TransactionId(
    val value: String
)