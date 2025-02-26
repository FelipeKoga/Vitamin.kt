package dev.koga.vitamin

import dev.koga.vitamin.di.appModule
import org.koin.core.Koin
import org.koin.core.context.startKoin

object Application {
    lateinit var koin: Koin

    fun init() {
        koin = startKoin {
            modules(appModule)
        }.koin
    }
}
