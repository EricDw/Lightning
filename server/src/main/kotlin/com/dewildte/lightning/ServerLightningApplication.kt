package com.dewildte.lightning

import com.dewildte.lightning.application.api.LightningApplication
import kotlinx.coroutines.flow.StateFlow

class ServerLightningApplication : LightningApplication {
    override val state: StateFlow<LightningApplication.State>
        get() = TODO("Not yet implemented")

    override suspend fun recieve(message: LightningApplication.Message) {
        when (message) {
            is LightningApplication.Message.GetPlatform -> TODO()
            is LightningApplication.Message.RetrieveTransactions -> TODO()
        }
    }
}