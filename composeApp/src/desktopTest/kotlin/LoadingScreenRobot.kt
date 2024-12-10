import androidx.compose.ui.test.*

@OptIn(ExperimentalTestApi::class)
class LoadingScreenRobot(
    private val test: ComposeUiTest
) {

    fun verifyIsDisplayed() {
        val description = "Lightning Bolt"
        val node = test.onNodeWithContentDescription(description)
        node.assertIsDisplayed()
    }

}

@OptIn(ExperimentalTestApi::class)
fun ComposeUiTest.withLoadingScreen(block: LoadingScreenRobot.() -> Unit) {
    LoadingScreenRobot(this).apply(block)
}