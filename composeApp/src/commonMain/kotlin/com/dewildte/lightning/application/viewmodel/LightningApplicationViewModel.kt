package com.dewildte.lightning.application.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dewildte.lightning.application.api.LightningApplication
import com.dewildte.lightning.design.components.LightningApplicationScaffoldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LightningApplicationViewModel(
    private val application: LightningApplication
): ViewModel() {

    private val _state = MutableStateFlow(LightningApplicationScaffoldState())
    val state: StateFlow<LightningApplicationScaffoldState> = _state.asStateFlow()

    fun recieve(message: Message) {
        when (message) {
            Message.GetPlatform -> {

                viewModelScope.launch {

                    application.recieve(
                        LightningApplication.Message.GetPlatform { platform ->

                            _state.update { oldState ->
                                oldState.copy()
                            }
                        }
                    )

                }

            }
        }
    }

    sealed class Message {
        data object GetPlatform : Message()
    }

}