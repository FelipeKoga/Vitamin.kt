package dev.koga.vitamin.model

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable) : Result<Nothing>
}

fun <T> Result<T>.getOrNull(): T? {
    return (this as? Result.Success)?.data
}