package com.github.mikibemiki

import kotlinx.coroutines.CancellationException

fun Throwable.throwIfCanceled() {
    if (this is CancellationException) throw this
}