package dev.koga.vitamin

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform