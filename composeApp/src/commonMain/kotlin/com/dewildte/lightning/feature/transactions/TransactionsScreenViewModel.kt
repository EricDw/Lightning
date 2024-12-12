package com.dewildte.lightning.feature.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dewildte.lightning.application.LightningApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TransactionsScreenViewModel(
    private val model: LightningApplication
) : ViewModel() {

    private val _state = MutableStateFlow(TransactionsScreenState())

    val state: StateFlow<TransactionsScreenState> =
        _state.asStateFlow()

    fun loadTransactions() {
        viewModelScope.launch {
            val message = LightningApplication.Message.RetrieveTransactions()

            model.recieve(message)

            try {
                val transactions = message.response.await()
                _state.update { oldState ->
                    oldState.copy(
                        isLoading = false,
                        transactions = transactions,
                    )
                }
            } catch (error: Throwable) {
                _state.update { oldState ->
                    oldState.copy(
                        isLoading = false,
                        error = error,
                    )
                }
            }
        }
    }

}