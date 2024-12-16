package com.dewildte.lightning

import com.dewildte.lightning.application.model.LightningApplication
impass WASMLightningApplication : LightningApplication {
    override suspend fun recieve(message: LightningApplication.Message) {
        TODO("Not yet implemented")
    }
}