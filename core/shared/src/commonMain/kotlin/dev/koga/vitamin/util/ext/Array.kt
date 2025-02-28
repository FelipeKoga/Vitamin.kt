@file:OptIn(ExperimentalJsExport::class)

package dev.koga.vitamin.util.ext

import kotlin.js.JsExport

@JsExport
fun <T> listOf(elements: Array<T>): List<T> = elements.toList()

@JsExport
fun <T> setOf(elements: Array<T>): Set<T> = elements.toSet()