package com.github.mikibemiki.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.test.Test
import kotlin.test.assertTrue

internal class ThrowableExtensionsTest {

    @Test
    fun testThrowIfCanceled() {
        var caught = false
        var thrown = true
        var finally = false
        val job = GlobalScope.launch {
            try {
                while (true) {
                    delay(100)
                }
            } catch (t: Throwable) {
                caught = true
                t.throwIfCanceled()
                thrown = false
            } finally {
                finally = true
            }
        }
        while (!job.isActive) {
            //Wait and block thread
        }
        job.cancel()
        while (!job.isCompleted) {
            //Wait and block thread
        }
        assertTrue(finally, "Finally is called.")
        assertTrue(caught, "Throwable was not caught.")
        assertTrue(thrown, "Throwable was thrown.")
    }

}