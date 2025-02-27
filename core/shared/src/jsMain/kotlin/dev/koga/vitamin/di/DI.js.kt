@file:OptIn(ExperimentalJsExport::class)

package dev.koga.vitamin.di

import dev.koga.vitamin.Application
import dev.koga.vitamin.repository.FruitRepositoryJs
import io.ktor.client.engine.js.Js
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { Js.create() }
    singleOf(::FruitRepositoryJs)
}

@JsExport
fun getFruitRepository(): FruitRepositoryJs {
    return Application.koin.get()
}