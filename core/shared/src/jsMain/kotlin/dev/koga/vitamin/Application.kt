@file:OptIn(ExperimentalJsExport::class)

package dev.koga.vitamin

@JsExport
fun initCoreModule() {
    CoreApplication.init()
}