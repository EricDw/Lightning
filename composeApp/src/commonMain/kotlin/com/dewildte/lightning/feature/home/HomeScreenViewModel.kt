package com.dewildte.lightning.feature.home

import androidx.lifecycle.ViewModel
import com.dewildte.lightning.application.model.LightningApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenViewModel(
    private val model: LightningApplication,
) : ViewModel() {

    private val _state =
        MutableStateFlow(HomeScreenState())

    val state: StateFlow<HomeScreenState> =
        _state.asStateFlow()


}