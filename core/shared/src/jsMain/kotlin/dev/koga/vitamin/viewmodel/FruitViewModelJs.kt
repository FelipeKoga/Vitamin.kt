@file:OptIn(ExperimentalJsExport::class)

package dev.koga.vitamin.viewmodel

import androidx.lifecycle.viewModelScope
import dev.koga.vitamin.Application
import dev.koga.vitamin.coroutines.FlowObserver
import dev.koga.vitamin.model.asJsResult

@JsExport
fun initApp() {
    Application.init()
}

@JsExport
class FruitViewModelJs {
    private val viewModel = Application.koin.get<FruitViewModel>()

    val fruitsState = FlowObserver(
        delegate = viewModel.fruitsStream.asJsResult(),
        coroutineScope = viewModel.viewModelScope
    )
}