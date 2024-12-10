package com.dewildte.lightning

import android.os.Build

data class AndroidPlatform(
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
) : Platform

actual fun getPlatform(): Platform = AndroidPlatform()