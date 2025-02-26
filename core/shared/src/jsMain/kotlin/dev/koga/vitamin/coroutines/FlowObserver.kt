@file:OptIn(ExperimentalJsExport::class)

package dev.koga.vitamin.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

@JsExport
interface FlowObserver<T> {
    fun stopObserving()
    fun startObserving(
        onEach: (T) -> Unit,
        onError: (Throwable) -> Unit = {},
        onComplete: () -> Unit = {},
    )
}


fun <T> FlowObserver(
    delegate: Flow<T>,
    coroutineScope: CoroutineScope
): FlowObserver<T> =
    FlowObserverImpl(delegate, coroutineScope)

class FlowObserverImpl<T>(
    private val delegate: Flow<T>,
    private val coroutineScope: CoroutineScope
) : FlowObserver<T> {
    private var observeJobs: List<Job> = emptyList()

    override fun startObserving(
        onEach: (T) -> Unit,
        onError: (Throwable) -> Unit,
        onComplete: () -> Unit,
    ) {
        observeJobs += delegate
            .onEach(onEach)
            .onCompletion { onComplete() }
            .catch { onError(it) }
            .launchIn(coroutineScope)
    }

    override fun stopObserving() {
        observeJobs.forEach { it.cancel() }
    }
}