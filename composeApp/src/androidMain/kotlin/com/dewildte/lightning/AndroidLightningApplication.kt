package com.dewildte.lightning

import android.app.Application
import com.dewildte.lightning.application.api.LightningApplication
import com.dewildte.lightning.network.FinanceApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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
                    val transactions = financeApi.retrieveAllTransactions()
                    message.response.complete(transactions)
                } catch (error: Throwable) {
                    message.response.completeExceptionally(error)
                }
            }
        }
    }
}