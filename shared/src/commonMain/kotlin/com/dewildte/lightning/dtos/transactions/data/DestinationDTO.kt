package com.dewildte.lightning.dtos.transactions.data

import kotlinx.serialization.Serializable

@Serializable
data class DestinationDTO(
    val value: String
)