package application.model

import com.dewildte.lightning.application.model.LightningApplication
import com.dewildte.lightning.models.users.User
import com.dewildte.lightning.models.users.UserId
import kotlin.uuid.Uuid

class TestLightningApplication : LightningApplication {
    override suspend fun recieve(message: LightningApplication.Message) {
        when (message) {
            is LightningApplication.Message.LoginWithEmailAndPassword -> {
                message.response.complete(User(id = UserId(Uuid.random().toString())))
            }
            is LightningApplication.Message.RetrieveTransactions -> {
                TODO()
            }
        }
    }
}