package com.training.nicklos.vmcolorlist.di

import android.support.test.espresso.idling.CountingIdlingResource
import com.training.nicklos.vmcolorlist.ui.colorlist.ColorListObserver
import com.training.nicklos.vmcolorlist.ui.colorlist.TestColorListObserver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Version of [ColorListObserverModule] for instrumented tests.
 *
 * Provides a [TestColorListObserver] as well as a [CountingIdlingResource]
 * to be used by the all test classes.
 */
@Module
class TestColorListObserverModule {

    @Singleton
    @Provides
    fun providesColorListObserver(idlingRes: CountingIdlingResource): ColorListObserver
            = TestColorListObserver(idlingRes)

    @Singleton
    @Provides
    fun providesIdlingResource() = CountingIdlingResource("COLOR_LIST")
}