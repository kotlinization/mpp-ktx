package com.kotlinization.mpp_ktx.extension

import kotlinx.coroutines.CancellationException

/**
 * Checks [this] and if its [CancellationException] throws it.
 */
fun Throwable.throwIfCanceled(): Throwable {
    if (this is CancellationException) throw this else return this
}