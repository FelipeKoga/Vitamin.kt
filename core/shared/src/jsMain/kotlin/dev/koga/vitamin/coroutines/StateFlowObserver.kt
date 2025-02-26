@file:OptIn(ExperimentalJsExport::class)

package dev.koga.vitamin.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@JsExport
interface StateFlowObserver<T> : FlowObserver<T> {
    val value: T
}

fun <T> StateFlowObserver(
    delegate: StateFlow<T>,
    coroutineScope: CoroutineScope
): StateFlowObserver<T> =
    StateFlowObserverImpl(delegate, coroutineScope)

class StateFlowObserverImpl<T>(
    private val delegate: StateFlow<T>,
    private val coroutineScope: CoroutineScope
) : StateFlowObserver<T> {
    private var jobs = mutableListOf<Job>()
    override val value: T
        get() = delegate.value

    override fun startObserving(
        onEach: (T) -> Unit,
        onError: (Throwable) -> Unit,
        onComplete: () -> Unit,
    ) {
        jobs += delegate
            .onEach(onEach)
            .launchIn(coroutineScope)
    }

    override fun stopObserving() {
        jobs.forEach { it.cancel() }
        jobs.clear()
    }
}