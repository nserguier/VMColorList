package com.training.nicklos.vmcolorlist

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner

/**
 * Custom runner for instrumented tests.
 * Allow to use the [TestColorListApp] that will bind dagger injections
 * specifically for tests.
 */
class EspressoTestRunner : AndroidJUnitRunner() {

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestColorListApp::class.java.name, context)
    }
}