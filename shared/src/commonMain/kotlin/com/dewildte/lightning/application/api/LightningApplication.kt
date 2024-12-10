package com.dewildte.lightning.application.api

import com.dewildte.lightning.Platform
import com.dewildte.lightning.finance.ReadTransaction
import com.dewildte.lightning.finance.TransactionRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface LightningApplication {

    val state : StateFlow<State>

    suspend fun recieve(message: Message)

    sealed class Message {

        data class GetPlatform(
            val response: (platform: Platform) -> Unit
        ): Message()

        data class RetrieveTransactions(
            val response: CompletableDeferred<List<ReadTransaction>> = CompletableDeferred()
        ): Message()
    }

    data class State(
        val isLoading: Boolean = true
    )

}