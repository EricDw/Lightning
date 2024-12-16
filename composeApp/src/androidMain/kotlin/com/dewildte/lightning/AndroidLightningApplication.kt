package com.dewildte.lightning

import android.app.Application
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.feature.transactions.data.TransactionMapper
import com.dewildte.lightning.network.FinanceApi

class AndroidLightningApplication : Application(), LightningApplication {

    private val financeApi by lazy { FinanceApi() }

    override fun onCreate() {
        super.onCreate()
    }

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