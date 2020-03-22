package com.github.mikibemiki.mppktx.coroutines

import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.yield

/**
 * Awaits until value in [this] channel is not null,
 * and returns value.
 */
suspend fun <T> ConflatedBroadcastChannel<T?>.awaitNonNull(): T {
    var channelValue: T? = valueOrNull
    while (channelValue == null) {
        yield()
        channelValue = valueOrNull
    }
    return channelValue
}