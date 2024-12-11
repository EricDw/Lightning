package com.dewildte.lightning

import com.dewildte.lightning.application.api.LightningApplication
import kotlinx.coroutines.flow.StateFlow

class WASMLightningApplication : LightningApplication {
    override suspend fun recieve(message: LightningApplication.Message) {
        TODO("Not yet implemented")
    }
}