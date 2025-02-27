@file:OptIn(ExperimentalJsExport::class)

package dev.koga.vitamin.model

@JsExport
class JsResult<T>(
    val loading: Boolean = false,
    val error: JsError? = null,
    val data: T? = null
)

@JsExport
class JsError(
    val message: String
)

fun <T> Result<T>.asJsResult(): JsResult<T> = when (this) {
    is Result.Error -> JsResult(error = JsError(this.exception.message.orEmpty()))
    is Result.Success -> JsResult(data = this.data)
}

fun <T> Result<List<T>>.asJsResult(): JsResult<Array<T>> = when (this) {
    is Result.Error -> JsResult(error = JsError(this.exception.message.orEmpty()))
    is Result.Success -> JsResult(data = this.data.toTypedArray())
}