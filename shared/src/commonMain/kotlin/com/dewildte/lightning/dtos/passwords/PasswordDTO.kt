package com.dewildte.lightning.dtos.passwords

import kotlinx.serialization.Serializable

@Serializable
data class PasswordDTO(
    val value: String
)