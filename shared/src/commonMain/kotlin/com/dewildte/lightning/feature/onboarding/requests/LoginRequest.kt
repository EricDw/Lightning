package com.dewildte.lightning.feature.onboarding.requests

import com.dewildte.lightning.dtos.email.EmailAddressDTO
import com.dewildte.lightning.dtos.passwords.PasswordDTO
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: EmailAddressDTO,
    val password: PasswordDTO,
)