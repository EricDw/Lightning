package com.dewildte.lightning

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform