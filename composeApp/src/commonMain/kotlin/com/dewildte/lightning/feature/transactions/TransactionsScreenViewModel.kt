package com.dewildte.lightning.feature.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.feature.transactions.model.Transaction
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
                val transactions = message
                    .response
                    .await()
                    .sortedBy {
                        it.date.value
                    }
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

    fun selectTransaction(transaction: Transaction) {
        _state.update { oldState ->
            oldState.copy(
                selectedTransaction = transaction,
            )
        }
    }

    fun updateSearchTerm(
        searchTerm: String
    ) {
        _state.update { oldState ->
            oldState.copy(
                searchTerm = searchTerm
            )
        }
    }

    fun searchTransactions(
        searchTerm: String
    ) {
        // TODO: Request the model to search for transactions
        _state.update { oldState ->
            val searchedTransactions = oldState.transactions.filter {
                it.source.value.contains(searchTerm, ignoreCase = true) ||
                it.destination.value.contains(searchTerm, ignoreCase = true) ||
                it.note.value.contains(searchTerm, ignoreCase = true) ||
                it.date.value.toString().contains(searchTerm, ignoreCase = true) ||
                it.money.value.toDouble().toString().contains(searchTerm)
            }

            oldState.copy(
                searchedTransactions = searchedTransactions
            )
        }
    }

    fun cancelSearch() {
        // TODO: Cancel the current search Request
        _state.update { oldState ->
            oldState.copy(
                searchedTransactions = listOf()
            )
        }

    }

}