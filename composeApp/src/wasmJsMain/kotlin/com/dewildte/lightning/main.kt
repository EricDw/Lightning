package com.dewildte.lightning

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.dewildte.lightning.application.LightningApplicationController
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val model = WASMLightningApplication()
    ComposeViewport(document.body!!) {
        LightningApplicationController(
            model = model
        )
    }
}