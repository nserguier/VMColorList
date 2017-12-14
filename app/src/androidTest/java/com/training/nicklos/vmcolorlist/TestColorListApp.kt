package com.training.nicklos.vmcolorlist

import com.training.nicklos.vmcolorlist.di.DaggerTestAppComponent

/**
 * Version of [ColorListApp] for instrumented tests.
 * Binds dagger injections specifically for tests.
 */
class TestColorListApp : ColorListApp() {

    override fun initializeComponent() {
        DaggerTestAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}