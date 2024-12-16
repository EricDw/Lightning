package com.dewildte.lightning.application

import androidx.lifecycle.ViewModel
import com.dewildte.lightning.application.model.LightningApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LightningApplicationViewModel(
    private val model: LightningApplication
) : ViewModel() {

    private val _state =
        MutableStateFlow(LightingApplicationState())

    val state: StateFlow<LightingApplicationState> =
        _state.asStateFlow()

    

}