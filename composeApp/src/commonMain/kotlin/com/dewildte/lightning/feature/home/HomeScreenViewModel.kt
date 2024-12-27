package com.dewildte.lightning.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.models.transactions.Transaction
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val model: LightningApplication,
) : ViewModel() {

    private val _state =
        MutableStateFlow(HomeScreenState())

    val state: StateFlow<HomeScreenState> =
        _state.asStateFlow()

    fun load() {
        viewModelScope.launch {
            _state.update { oldState ->
                oldState.copy(isLoading = true)
            }

            val message = LightningApplication.Message.RetrieveTransactions()

            model.recieve(message)

            try {

                val transactions = message.response.await()

                _state.update { oldState ->
                    oldState.copy(
                        isLoading = false,
                        transactions = transactions.toPersistentList()
                    )
                }

            } catch (e: Throwable) {
                _state.update { oldState ->
                    oldState.copy(
                        isLoading = false,
                        error = HomeScreenError.UknownError(cause = e.message.toString())
                    )
                }
            }

        }
    }

    fun selectTransaction(
        transaction : Transaction
    ) {
        _state.update { oldState ->
            oldState.copy(
                selectedTransaction = transaction
            )
        }
    }

}