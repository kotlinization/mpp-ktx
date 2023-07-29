package com.kotlinization.mpp_ktx.android.example.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.util.Log
import com.kotlinization.kotlinizer.mpp_ktx.service.BoundService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.isActive
import kotlin.random.Random

private const val TAG = "ExampleService"

class ExampleServiceOneImpl : Service(), ExampleServiceOne {

    override val timeFlow: Flow<String>
        get() = channelFlow {
            while (isActive) {
                send(System.currentTimeMillis().toString())
                delay(1000)
            }
        }

    private val exampleBinder by lazy {
        ExampleBinder(this)
    }

    override fun onBind(intent: Intent): ExampleBinder {
        Log.i(TAG, "Service bound.")
        return exampleBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "Service unbound.")
        return super.onUnbind(intent)
    }

    override suspend fun generateString(): String {
        return Random.nextInt().toString()
    }
}

interface ExampleServiceOne {

    val timeFlow: Flow<String>

    suspend fun generateString(): String
}

class ExampleBinder(
    private val exampleServiceOne: ExampleServiceOne
) : Binder(), ExampleServiceOne by exampleServiceOne

class ExampleServiceBound(
    context: Context,
    scope: CoroutineScope
) : BoundService<ExampleBinder>(
    context = context,
    scope = scope,
    binding = {
        context.bindService(Intent(context, ExampleServiceOneImpl::class.java), it, Service.BIND_AUTO_CREATE)
    }
)

