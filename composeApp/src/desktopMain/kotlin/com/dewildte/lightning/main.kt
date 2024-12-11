package com.dewildte.lightning

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.dewildte.lightning.application.LightningApplicationController
import lightning.composeapp.generated.resources.Res
import lightning.composeapp.generated.resources.title_app_name
import org.jetbrains.compose.resources.stringResource

fun main() = application {

    val model = remember(this) {
        DesktopLightningApplication(
            applicationScope = this
        )
    }

    val title = stringResource(Res.string.title_app_name)

    val mainWindowState = rememberWindowState(
        position = WindowPosition(Alignment.Center)
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = title,
        state = mainWindowState,
        icon = rememberVectorPainter(Icons.Default.Bolt)
    ) {
        LightningApplicationController(
            model = model
        )
    }

}