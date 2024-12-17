package com.dewildte.lightning.dtos.email

import kotlinx.serialization.Serializable

@Serializable
data class EmailAddressDTO(
    val value: String
)