package com.github.mikibemiki.coroutines.mppktx

import kotlinx.coroutines.CancellationException

/**
 * Checks [this] and if its [CancellationException] throws it.
 */
fun Throwable.throwIfCanceled() {
    if (this is CancellationException) throw this
}