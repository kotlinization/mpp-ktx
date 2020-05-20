package com.github.kotlinizer.mppktx.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow

/**
 * Awaits until value in [this] channel is not null,
 * and returns value.
 */
suspend fun <T> StateFlow<T?>.awaitNonNull(): T {
    var flowValue: T? = value
    while (flowValue == null) {
        delay(1)
        flowValue = value
    }
    return flowValue
}