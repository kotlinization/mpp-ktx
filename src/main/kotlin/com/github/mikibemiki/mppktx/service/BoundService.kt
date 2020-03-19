package com.github.mikibemiki.mppktx.service

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import kotlinx.coroutines.*

open class BoundService<B : IBinder>(
    context: Context,
    scope: CoroutineScope,
    binding: (serviceConnection: ServiceConnection) -> Unit
) {

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            deferrable = CompletableDeferred()
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            @Suppress("UNCHECKED_CAST")
            deferrable.complete(service as B)
        }
    }

    private var deferrable: CompletableDeferred<B> = CompletableDeferred()

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
}

