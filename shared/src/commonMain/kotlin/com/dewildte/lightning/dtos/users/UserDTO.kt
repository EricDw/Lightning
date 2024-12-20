package com.dewildte.lightning.dtos.users

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val id: UserIdDTO,
)