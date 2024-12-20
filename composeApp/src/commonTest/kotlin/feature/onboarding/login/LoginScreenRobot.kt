package feature.onboarding.login

import androidx.compose.ui.test.*

class LoginScreenRobot(
    val test: ComposeUiTest
) {

    private val emailField: SemanticsNodeInteraction
        get() {
            return test.onNodeWithText("Email")
        }

    private val passwordField: SemanticsNodeInteraction
        get() {
            return test.onNodeWithText("Password")
        }

    private val loginButton: SemanticsNodeInteraction
        get() {
            return test.onNodeWithText("Login")
        }

    fun verifyIsDisplayed() {
        emailField.assertIsDisplayed()
    }

    fun loginWithEmailAndPassword(
        email: String,
        password: String
    ) {
        with(emailField) {
            performTextInput(email)
            assertTextContains(email)
        }
        with(passwordField) {
            performTextInput(password)
            val maskedPassword = password.map { '•' }.joinToString(separator = "")
            assertTextContains(maskedPassword)
        }
        test.waitForIdle()
        loginButton.performClick()
    }

}

fun ComposeUiTest.withLoginScreen(block: LoginScreenRobot.() -> Unit) {
    LoginScreenRobot(test = this).apply(block)
}