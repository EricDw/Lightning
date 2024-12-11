package com.dewildte.lightning.network

import com.dewildte.lightning.finance.TransactionDTO
import com.dewildte.lightning.finance.IdentifierDTO
import com.dewildte.lightning.finance.TransactionModel
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

    suspend fun retrieveAllTransactions(): List<TransactionDTO> {
        return httpClient.get("/finance/transactions").body()
    }

    suspend fun insertTransaction(transaction: TransactionModel) {

    }

    suspend fun deleteTransaction(identifierDTO: IdentifierDTO) {

    }

}