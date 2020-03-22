package com.github.mikibemiki.android.example.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.util.Log
import com.github.mikibemiki.mppktx.service.BoundService
import kotlinx.coroutines.CoroutineScope
import kotlin.random.Random

private const val TAG = "ExampleService"

class ExampleServiceOne : Service() {

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

    fun generateString(): String {
        return Random.nextInt().toString()
    }
}

class ExampleBinder(private val exampleServiceOne: ExampleServiceOne) : Binder() {

    fun generateString(): String {
        return exampleServiceOne.generateString()
    }
}

class ExampleServiceBound(
    context: Context,
    scope: CoroutineScope
) : BoundService<ExampleBinder>(
    context = context,
    scope = scope,
    binding = {
        context.bindService(Intent(context, ExampleServiceOne::class.java), it, Service.BIND_AUTO_CREATE)
    }
)

