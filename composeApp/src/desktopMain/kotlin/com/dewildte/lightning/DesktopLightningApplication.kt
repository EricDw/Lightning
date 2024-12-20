package com.dewildte.lightning

import androidx.compose.ui.window.ApplicationScope
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.network.FinanceApi
import com.dewildte.lightning.network.TransactionMapper
import com.dewildte.lightning.network.buildHttpClient

class DesktopLightningApplication(
    applicationScope: ApplicationScope
) : ApplicationScope by applicationScope, LightningApplication {
    private val httpClient by lazy {
        buildHttpClient()
    }

    private val financeApi by lazy {
        FinanceApi(
            httpClient = httpClient
        )
    }

    override suspend fun recieve(message: LightningApplication.Message) {
        when (message) {

            is LightningApplication.Message.RetrieveTransactions -> {
                try {
                    val mapper = TransactionMapper()
                    val transactions = financeApi.retrieveAllTransactions()
                        .map(mapper::mapTransactionDtoToTransaction)
                    message.response.complete(transactions)
                } catch (error: Throwable) {
                    message.response.completeExceptionally(error)
                }
            }

            is LightningApplication.Message.LoginWithEmailAndPassword -> {
                TODO()
            }
        }
    }
}