package com.dewildte.lightning.feature.onboarding.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dewildte.lightning.application.model.LightningApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val model: LightningApplication
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

    fun loginWithEmailAndPassword() {
        viewModelScope.launch {

            val email = _state.value.email
            val password = _state.value.password
            val message = LightningApplication.Message.LoginWithEmailAndPassword(
                email = email,
                password = password
            )

            model.recieve(message = message)

            try {
                val user = message.response.await()
                _state.update { oldState ->
                    oldState.copy(
                        user = user,
                    )
                }
            } catch (e: Throwable) {
                _state.update { oldState ->
                    oldState.copy(
                        error = e.message
                    )
                }
            }

        }
    }

}