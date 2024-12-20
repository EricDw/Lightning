package feature.home

import androidx.compose.ui.test.*

class HomeScreenRobot(
    val test: ComposeUiTest
) {
    private val homeIcon: SemanticsNodeInteraction
        get() {
            return test.onNodeWithContentDescription("Home")
        }

    fun verifyIsDisplayed() {
        with(homeIcon) {
            assertExists()
            assertIsDisplayed()
            assertIsSelected()
        }
    }

}

fun ComposeUiTest.withHomeScreen(block: HomeScreenRobot.() -> Unit) {
    HomeScreenRobot(test = this).apply(block)
}