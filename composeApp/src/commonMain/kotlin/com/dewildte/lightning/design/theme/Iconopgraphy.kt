package com.dewildte.lightning.design.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector

@Immutable
data class Iconopgraphy(
    val home: ImageVector = Icons.Default.Cabin,
    val lightning: ImageVector = Icons.Default.Bolt,
    val clear: ImageVector = Icons.Default.Clear,
    val revealPassword: ImageVector = Icons.Default.Visibility,
    val hidePassword: ImageVector = Icons.Default.VisibilityOff,
)

val LocalIconopgraphy = staticCompositionLocalOf { Iconopgraphy() }

val MaterialTheme.icons: Iconopgraphy
    @Composable get() = LocalIconopgraphy.current