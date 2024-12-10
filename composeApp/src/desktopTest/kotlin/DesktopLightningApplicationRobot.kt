import androidx.compose.ui.test.DesktopComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import application.robot.LightningApplicationRobot
import com.dewildte.lightning.DesktopLightningApplication
import com.dewildte.lightning.DesktopLightningApplication

@OptIn(ExperimentalTestApi::class)
class DesktopLightningApplicationRobot(
    private val test: DesktopComposeUiTest
) : LightningApplicationRobot {

    private val model = DesktopLightningApplication()

    override fun launchApp() {
        test.setContent {
            DesktopLightningApplication(
                application = model,
                exitApplication = {
                    // TODO: Track the exit
                }
            )
        }
    }

}

@OptIn(ExperimentalTestApi::class)
fun DesktopComposeUiTest.withApplication(
    initializer: DesktopLightningApplicationRobot.() -> Unit,
): LightningApplicationRobot {
    return DesktopLightningApplicationRobot(this).apply(initializer)
}