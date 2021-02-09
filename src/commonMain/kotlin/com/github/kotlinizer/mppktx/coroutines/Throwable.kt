package com.github.kotlinizer.mppktx.coroutines

import kotlinx.coroutines.CancellationException

/**
 * Checks [this] and if its [CancellationException] throws it.
 */
fun Throwable.throwIfCanceled(): Throwable {
    if (this is CancellationException) throw this else return this
}