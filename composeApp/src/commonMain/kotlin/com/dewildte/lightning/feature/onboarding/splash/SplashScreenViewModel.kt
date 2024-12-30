package com.dewildte.lightning.feature.onboarding.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dewildte.lightning.application.model.LightningApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val model: LightningApplication,
) : ViewModel() {

    private val _state = MutableStateFlow<SplashScreenState>(
        value = SplashScreenState.Intialized
    )

    val state: StateFlow<SplashScreenState> =
        _state.asStateFlow()

    fun tryLogin() {
        viewModelScope.launch {
            val message = LightningApplication.Message.TryLogin()
            model.recieve(message)
            try {
                val user = message.response.await()
                _state.update {
                    SplashScreenState.UserFound(user)
                }
            } catch (e: Throwable) {
                _state.update {
                    SplashScreenState.UserNotFound
                }
            }
        }
    }

}