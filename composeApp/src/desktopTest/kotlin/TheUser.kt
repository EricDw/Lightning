import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runDesktopComposeUiTest
import com.dewildte.lightning.application.api.LightningApplication
import kotlinx.coroutines.flow.StateFlow
import kotlin.test.Test

/**
 * Defines tests for the critical journeys the user can take through the application.
 */
@OptIn(ExperimentalTestApi::class)
class TheUser {

    @Test
    fun canAddNewMarkdownFiles() = runDesktopComposeUiTest {
        // Arrange
        val fileName = "LightningTestFile"

        val model = object : LightningApplication {
            override val state: StateFlow<LightningApplication.State>
                get() = TODO("Not yet implemented")

            override suspend fun recieve(message: LightningApplication.Message) {
                TODO("Not yet implemented")
            }

        }

        // Act
        val applicationRobot = withApplication {
            launchApp()
        }

        // Assert
        withLoadingScreen {
            verifyIsDisplayed()
        }

    }
}