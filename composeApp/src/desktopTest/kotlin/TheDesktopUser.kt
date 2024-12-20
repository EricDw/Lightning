import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.window.Window
import application.model.TestLightningApplication
import com.dewildte.lightning.application.LightningApplicationController
import feature.home.withHomeScreen
import feature.onboarding.login.withLoginScreen
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class TheDesktopUser {

    @Test
    fun canLogin() = runComposeUiTest {
        // Arrange
        val model = TestLightningApplication()
        val email = "test@test.com"
        val password = "Test Password"

        // Act
        setContent {
            Window(
                onCloseRequest = { /* no-op */ },
            ) {
                LightningApplicationController(
                    model = model
                )
            }
        }

        withLoginScreen {
            verifyIsDisplayed()
            loginWithEmailAndPassword(
                email = email,
                password = password,
            )
        }

        // Assert
        withApplicationRobot {
            verifyHomeIsSelected()
        }

    }
}