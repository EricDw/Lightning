package application.model

import com.dewildte.lightning.application.model.LightningApplication

class TestLightningApplication : LightningApplication {
    override suspend fun recieve(message: LightningApplication.Message) {
        when (message) {
            else -> {
                TODO("Not yet implemented")
            }
        }
    }
}