package com.dewildte.lightning.models.tags

import androidx.compose.runtime.Immutable

@Immutable
data class Tag(
    val id: TagId,
    val label: TagLabel,
)