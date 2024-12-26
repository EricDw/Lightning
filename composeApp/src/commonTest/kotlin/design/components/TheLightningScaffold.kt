package design.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.test.*
import com.dewildte.lightning.application.AppDestination
import com.dewildte.lightning.design.components.LightningScaffold
import utils.hasRole
import kotlin.test.Test

class TheLightningScaffold {

    @Test
    fun canHideNavigationControls() = runComposeUiTest {
        // Arrange
        val selectedDestination = null

        // Act
        setContent {
            LightningScaffold(
                selectedDestination = selectedDestination,
            )
        }

        // Assert
        onTabNodeWithText("Home")
            .assertDoesNotExist()

        onTabNodeWithText("Transactions")
            .assertDoesNotExist()

        onTabNodeWithText("Settings")
            .assertDoesNotExist()
    }

    @Test
    fun canDisplayNavigationControls() = runComposeUiTest {
        // Arrange
        val selectedDestination = AppDestination.HOME

        // Act
        setContent {
            LightningScaffold(
                modifier = Modifier.fillMaxSize(),
                selectedDestination = selectedDestination
            )
        }

        // Assert
        onTabNodeWithText("Home")
            .assertExists()
            .assertIsDisplayed()
            .assertIsSelected()

        onTabNodeWithText("Transactions")
            .assertExists()
            .assertIsDisplayed()
            .assertIsNotSelected()

        onTabNodeWithText("Settings")
            .assertExists()
            .assertIsDisplayed()
            .assertIsNotSelected()
    }

    private fun SemanticsNodeInteractionsProvider.onTabNodeWithText(
        text: String
    ): SemanticsNodeInteraction {
        return onNode(hasText(text) and hasRole(Role.Tab))
    }

}