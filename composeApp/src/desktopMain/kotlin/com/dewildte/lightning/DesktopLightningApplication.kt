package com.dewildte.lightning

import androidx.compose.ui.window.ApplicationScope
import com.dewildte.lightning.application.api.LightningApplication
import com.dewildte.lightning.network.FinanceApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DesktopLightningApplication(
    applicationScope: ApplicationScope
) : ApplicationScope by applicationScope, LightningApplication {

    private val financeApi by lazy { FinanceApi() }

    private val _state = MutableStateFlow(LightningApplication.State())
    override val state: StateFlow<LightningApplication.State> =
        _state.asStateFlow()

    override suspend fun recieve(message: LightningApplication.Message) {
        when (message) {
            is LightningApplication.Message.GetPlatform -> {
                delay(3000)
                _state.update { oldState ->
                    oldState.copy(
                        isLoading = false
                    )
                }
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