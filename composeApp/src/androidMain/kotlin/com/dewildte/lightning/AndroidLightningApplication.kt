package com.dewildte.lightning

import android.app.Application
import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.models.users.User
import com.dewildte.lightning.models.users.UserId
import com.dewildte.lightning.network.TransactionMapper
import com.dewildte.lightning.network.FinanceApi
import com.dewildte.lightning.network.buildHttpClient

class AndroidLightningApplication : Application(), LightningApplication {

    private val httpClient by lazy {
        buildHttpClient()
    }

    private val financeApi by lazy {
        FinanceApi(
            httpClient = httpClient
        )
    }

    override fun onCreate() {
        super.onCreate()
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
                val user = User(
                    id = UserId(value = "randomUserId")
                )
                message.response.complete(user)
            }
        }
    }
}