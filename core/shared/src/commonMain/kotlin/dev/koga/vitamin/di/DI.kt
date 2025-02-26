package dev.koga.vitamin.di

import dev.koga.vitamin.network.FruitApi
import dev.koga.vitamin.viewmodel.FruitViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf

val appModule = module {
    single {
        HttpClient(engine = get()) {
            install(ContentNegotiation) {
                json(
                    Json { isLenient = true; ignoreUnknownKeys = true }
                )
            }
        }
    }

    singleOf(::FruitApi)
    viewModelOf(::FruitViewModel)
    includes(platformModule)

}

expect val platformModule: Module