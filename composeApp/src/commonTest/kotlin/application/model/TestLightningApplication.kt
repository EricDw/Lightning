package application.model

import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.getPlatform

class TestLightningApplication : LightningApplication {
    override suspend fun recieve(message: LightningApplication.Message) {
        when (message) {
            is LightningApplication.Message.GetPlatform -> {
                val platform = getPlatform()
                message.response.complete(platform)
            }
            else -> {
                TODO("Not yet implemented")
            }
        }
    }
}