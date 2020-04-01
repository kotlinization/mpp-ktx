package com.github.mikibemiki.mppktx.coroutines

import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.delay

/**
 * Awaits until value in [this] channel is not null,
 * and returns value.
 */
suspend fun <T> ConflatedBroadcastChannel<T?>.awaitNonNull(): T {
    var channelValue: T? = valueOrNull
    while (channelValue == null) {
        delay(1)
        channelValue = valueOrNull
    }
    return channelValue
}