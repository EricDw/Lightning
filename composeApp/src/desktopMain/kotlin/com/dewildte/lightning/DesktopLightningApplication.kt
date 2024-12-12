package com.dewildte.lightning

import androidx.compose.ui.window.ApplicationScope
import com.dewildte.lightning.application.LightningApplication
import com.dewildte.lightning.feature.transactions.data.TransactionMapper
import com.dewildte.lightning.network.FinanceApi

class DesktopLightningApplication(
    applicationScope: ApplicationScope
) : ApplicationScope by applicationScope, LightningApplication {

    private val financeApi by lazy { FinanceApi() }

    override suspend fun recieve(message: LightningApplication.Message) {
        when (message) {
            is LightningApplication.Message.GetPlatform -> {
                val platform = getPlatform()
                message.response.complete(platform)
            }

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
        }
    }
}