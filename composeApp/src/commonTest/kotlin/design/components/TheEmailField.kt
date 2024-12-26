package design.components

import androidx.compose.ui.test.*
import com.dewildte.lightning.design.components.EmailField
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class TheEmailField {


    @Test
    fun containsContentDescription() = runComposeUiTest {
        // Arrange
        val expected = "Email Field"

        // Assert
        setContent {
            EmailField()
        }

        // Act
        onNodeWithContentDescription(expected)
            .assertExists()
            .assertIsDisplayed()
    }

}