package com.dewildte.lightning.feature.tags.model

import androidx.compose.runtime.Immutable

@Immutable
data class Tag(
    val id: TagId,
    val label: TagLabel,
)