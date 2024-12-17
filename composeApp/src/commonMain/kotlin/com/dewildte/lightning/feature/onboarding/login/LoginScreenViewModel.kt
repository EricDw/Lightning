package com.dewildte.lightning.feature.onboarding.login

import androidx.lifecycle.ViewModel
import com.dewildte.lightning.application.model.LightningApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginScreenViewModel(
    model: LightningApplication
) : ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state: StateFlow<LoginScreenState> =
        _state.asStateFlow()

    fun setEmail(
        newEmail: String
    ) {
        _state.update { oldState ->
            oldState.copy(
                email = newEmail
            )
        }
    }

    fun setPassword(
        newPassword: String
    ) {
        _state.update { oldState ->
            oldState.copy(
                password = newPassword
            )
        }
    }

}