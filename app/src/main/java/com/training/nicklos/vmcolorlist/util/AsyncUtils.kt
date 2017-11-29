package com.training.nicklos.vmcolorlist.util

import android.os.AsyncTask

/**
 * Calls the specified function [block] in an asyncTask (background work)
 */
fun async(block: () -> Unit) {
    AsyncTask.execute { block() }
}