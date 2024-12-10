package com.dewildte.lightning

import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.dewildte.lightning.design.components.LightningApplicationScaffold
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        val model = remember {
            WASMLightningApplication()
        }
        LightningApplicationScaffold(
            model = model
        )
    }
}