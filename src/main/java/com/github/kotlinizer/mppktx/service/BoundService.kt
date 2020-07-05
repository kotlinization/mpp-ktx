package com.github.kotlinizer.mppktx.service

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

open class BoundService<B : IBinder>(
    context: Context,
    scope: CoroutineScope,
    @PublishedApi
    internal val boundTimeout: Long = 1_000L,
    binding: (serviceConnection: ServiceConnection) -> Unit
) {

    companion object {

        /**
         * Creates service with temporary coroutine scope and cancels that scope
         * as soon [block] is finished.
         */
        suspend fun <T, B : IBinder, S : BoundService<B>> withService(
            boundService: (CoroutineScope) -> S,
            block: suspend (S) -> T
        ): T {
            val scope = CoroutineScope(Dispatchers.IO)
            try {
                return block(boundService(scope))
            } finally {
                scope.cancel()
            }
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            binderChannel.value = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            @Suppress("UNCHECKED_CAST")
            binderChannel.value = service as? B
        }
    }

    @PublishedApi
    internal val binderChannel = MutableStateFlow<B?>(null)

    init {
        //Launch Job which waits until scope is canceled and then unbinds service
        scope.launch {
            try {
                delay(Long.MAX_VALUE)
            } catch (c: CancellationException) {
                context.unbindService(serviceConnection)
            }
        }
        binding(serviceConnection)
    }

    /**
     * Waits [boundTimeout] for service to bind. If it fails throws
     * [TimeoutCancellationException]
     */
    suspend fun <T> invokeDelayed(block: suspend B.() -> T): T {
        val service = withTimeout(boundTimeout) {
            binderChannel.filterNotNull().first()
        }
        return block(service)
    }

    /**
     * Calls [invokeDelayed] with provided [block].
     */
    suspend inline operator fun <T> invoke(noinline block: suspend B.() -> T): T {
        return invokeDelayed(block)
    }

    /**
     * Maps flows from service after connects or reconnects to resulting flow.
     */
    fun <T> mapFlow(block: suspend B.() -> Flow<T>): Flow<T> {
        return binderChannel
            .filterNotNull()
            .flatMapLatest {
                block(it)
            }
    }
}

