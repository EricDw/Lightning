package com.dewildte.lightning.feature.transactions.model

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDate

@Immutable
data class TransactionDate(
    val value: LocalDate
)