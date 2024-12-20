package com.dewildte.lightning.models.transactions

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDate

@Immutable
data class TransactionDate(
    val value: LocalDate
)