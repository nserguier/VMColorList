package com.training.nicklos.vmcolorlist

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

/**
 * Executor to execute code on the main thread
 */
class MainThreadExecutor : Executor {
    private val mainThreadHandler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        mainThreadHandler.post(command)
    }
}