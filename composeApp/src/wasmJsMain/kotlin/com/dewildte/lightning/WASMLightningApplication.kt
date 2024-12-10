package com.dewildte.lightning

import com.dewildte.lightning.application.api.LightningApplication
import kotlinx.coroutines.flow.StateFlow

class WASMLightningApplication : LightningApplication {
    override val state: StateFlow<LightningApplication.State>
        get() = TODO("Not yet implemented")

    override suspend fun recieve(message: LightningApplication.Message) {
        TODO("Not yet implemented")
    }
}