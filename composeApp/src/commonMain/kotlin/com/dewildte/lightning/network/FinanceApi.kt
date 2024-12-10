package com.dewildte.lightning.network

import com.dewildte.lightning.finance.ReadTransaction
import com.dewildte.lightning.finance.TransactionId
import com.dewildte.lightning.finance.WriteTransaction
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class FinanceApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                json = Json {
                    encodeDefaults = true
                    isLenient = true
                    coerceInputValues = true
                    ignoreUnknownKeys = true
                },
            )
        }
        defaultRequest {
            host = LOCAL_HOST
            port = 8080
        }
    }

    suspend fun retrieveAllTransactions(): List<ReadTransaction> {
        return httpClient.get("/finance/transactions").body()
    }

    suspend fun insertTransaction(transaction: WriteTransaction) {

    }

    suspend fun deleteTransaction(transactionId: TransactionId) {

    }

}