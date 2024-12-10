package application.model

import com.dewildte.lightning.application.api.LightningApplication
import com.dewildte.lightning.getPlatform
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TestLightningApplication : LightningApplication {

    private val _state = MutableStateFlow(LightningApplication.State())
    override val state: StateFlow<LightningApplication.State> =
        _state.asStateFlow()


    override suspend fun recieve(message: LightningApplication.Message) {
        when (message) {
            is LightningApplication.Message.GetPlatform -> {
                val platform = getPlatform()
                message.response(platform)
            }
            else -> {
                TODO("Not yet implemented")
            }
        }
    }
}