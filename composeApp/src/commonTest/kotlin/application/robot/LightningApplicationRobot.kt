import androidx.compose.ui.test.*

class LightningApplicationRobot(
    private val test: ComposeUiTest
) {

    private val homeIcon: SemanticsNodeInteraction
        get() {
            return test.onNodeWithText("Home", useUnmergedTree = true)
        }


    fun verifyHomeIsSelected() {
        test.waitForIdle()
        with(homeIcon) {
            assertExists()
            assertIsDisplayed()
            assertIsSelected()
        }
    }

}

fun ComposeUiTest.withApplicationRobot(
    initializer: LightningApplicationRobot.() -> Unit
): LightningApplicationRobot {
    return LightningApplicationRobot(this).apply(initializer)
}