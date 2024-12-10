package com.dewildte.lightning.design.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable

/**
 * <a href="https://material.io/design/material-theming/overview.html" class="external" target="_blank">Material Theming</a>.
 *
 * Lightning Theming refers to the customization of the applications design to better reflect the
 * product’s brand.
 *
 * Lightning components use values provided here when retrieving
 * default values.
 *
 * It defines colors as specified in the [Material Color theme creation spec](https://material.io/design/color/the-color-system.html#color-theme-creation),
 * typography defined in the [Material Type Scale spec](https://material.io/design/typography/the-type-system.html#type-scale),
 * and shapes defined in the [Shape scheme](https://material.io/design/shape/applying-shape-to-ui.html#shape-scheme).
 * This is because Lightning is based on the [Material Components for Android](https://material.io/develop/android/docs/getting-started/)
 *
 * All values may be set by providing this component with the [colors][Colors],
 * [typography][Typography], and [shapes][Shapes] attributes. Use this to configure the overall
 * theme of elements within this MaterialTheme.
 *
 * Any values that are not set will inherit the current value from the theme, falling back to the
 * defaults if there is no parent MaterialTheme. This allows using a LightningTheme at the top
 * of your application, and then separate MaterialTheme(s) for different screens / parts of your
 * UI, overriding only the parts of the theme definition that need to change.
 *
 * @param colors A complete definition of the Material Color theme for this hierarchy
 * @param typography A set of text styles to be used as this hierarchy's typography system
 * @param shapes A set of shapes to be used by the components in this hierarchy

 */
@Composable
fun LightningTheme(
 colors: ColorScheme = MaterialTheme.colorScheme,
 typography: Typography = MaterialTheme.typography,
 shapes: Shapes = MaterialTheme.shapes,
 content: @Composable () -> Unit,
) {
 MaterialTheme(
  colorScheme = colors,
  typography = typography,
  shapes = shapes,
  content = content,
 )
}