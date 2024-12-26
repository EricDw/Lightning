package com.dewildte.lightning.design.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TwoPaneLayout(
    twoPane: Boolean,
    showSecondaryContent: Boolean,
    primaryContent: @Composable () -> Unit,
    secondaryContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (twoPane) {
        Row(
            modifier = modifier,
        ) {
            Box(modifier = Modifier.weight(0.5F)) {
                primaryContent()
            }
            Spacer(modifier = Modifier.width(width = 16.dp))
            Box(
                modifier = Modifier.weight(0.5F)
            ) {
                secondaryContent()
            }
        }
    } else {
        Box(
            modifier = modifier,
        ) {
            if (showSecondaryContent) {
                secondaryContent()
            } else {
                primaryContent()
            }
        }
    }

}
