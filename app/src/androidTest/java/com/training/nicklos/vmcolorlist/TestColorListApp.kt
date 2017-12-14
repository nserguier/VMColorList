package com.training.nicklos.vmcolorlist

import com.training.nicklos.vmcolorlist.di.DaggerTestAppComponent
import com.training.nicklos.vmcolorlist.di.TestAppComponent

/**
 * Version of [ColorListApp] for instrumented tests.
 * Binds dagger injections specifically for tests.
 */
class TestColorListApp : ColorListApp() {

    companion object {
        @JvmStatic lateinit var component: TestAppComponent
    }

    override fun initializeComponent() {
        component = DaggerTestAppComponent.builder()
                .application(this)
                .build()
        component.inject(this)
    }
}