package com.dewildte.lightning.feature.onboarding.responses

import com.dewildte.lightning.dtos.users.UserDTO
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val user: UserDTO
)