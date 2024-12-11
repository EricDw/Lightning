package com.dewildte.lightning.finance

import kotlinx.serialization.Serializable

@Serializable
data class TagDTO(
    val identifierDTO: IdentifierDTO,
    val label: String,
)