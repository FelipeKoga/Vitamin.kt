@file:OptIn(ExperimentalJsExport::class)

package dev.koga.vitamin.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@JsExport
class JsResult<T>(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val data: T? = null
)

fun <T> Flow<Result<T>>.asJsResult(): Flow<JsResult<T>> = map {
    when (it) {
        is Result.Error -> JsResult(isError = true)
        Result.Loading -> JsResult(isLoading = true)
        is Result.Success -> JsResult(data = it.data)
    }
}

fun <T> Flow<Result<List<T>>>.asJsResult(): Flow<JsResult<Array<T>>> = map {
    when (it) {
        is Result.Error -> JsResult(isError = true)
        Result.Loading -> JsResult(isLoading = true)
        is Result.Success -> JsResult(data = it.data.toTypedArray())
    }
}