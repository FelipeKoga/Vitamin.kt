@file:OptIn(ExperimentalJsExport::class)

package dev.koga.vitamin.repository

import dev.koga.vitamin.CoreApplication
import dev.koga.vitamin.model.Fruit
import dev.koga.vitamin.model.getOrNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.promise
import kotlin.js.Promise

@JsExport
class FruitRepositoryJs {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val repository = CoreApplication.koin.get<FruitRepository>()

    fun getAll(): Promise<Array<Fruit>> {
        return coroutineScope.promise {
            repository.getAll().getOrNull()?.toTypedArray() ?: emptyArray()
        }
    }

    fun getByName(name: String): Promise<Fruit?> {
        return coroutineScope.promise {
            repository.getByName(name).getOrNull()
        }
    }
}