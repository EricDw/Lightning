package com.dewildte.lightning.application.api

import com.dewildte.lightning.Platform
import com.dewildte.lightning.finance.TransactionDTO
import com.dewildte.lightning.finance.TransactionRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface LightningApplication {

    suspend fun recieve(message: Message)

    sealed class Message {

        data class GetPlatform(
            val response: CompletableDeferred<Platform> = CompletableDeferred()
        ): Message()

        data class RetrieveTransactions(
            val response: CompletableDeferred<List<TransactionDTO>> = CompletableDeferred()
        ): Message()
    }

    data class State(
        val isLoading: Boolean = true
    )

}