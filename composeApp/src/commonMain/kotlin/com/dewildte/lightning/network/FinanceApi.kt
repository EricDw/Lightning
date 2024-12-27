package com.dewildte.lightning.network

import com.dewildte.lightning.dtos.transactions.data.TransactionDTO
import com.dewildte.lightning.dtos.transactions.data.TransactionIdDTO
import com.dewildte.lightning.models.transactions.Transaction
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class FinanceApi(
    private val httpClient: HttpClient
) {

    suspend fun getTransactions(
        username: String,
        password: String,
    ): List<TransactionDTO> {
        return httpClient.get("/finance/transactions") {
            basicAuth(
                username = username,
                password = password,
            )
        }.body()
    }

    suspend fun putTransaction(transaction: Transaction) {

    }

    suspend fun deleteTransaction(transactionIdDTO: TransactionIdDTO) {
    }

}