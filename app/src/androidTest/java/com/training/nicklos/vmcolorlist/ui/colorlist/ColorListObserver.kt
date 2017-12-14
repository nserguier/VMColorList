package com.training.nicklos.vmcolorlist.ui.colorlist

import android.support.test.espresso.idling.CountingIdlingResource

/**
 * Version of [ColorListObserver] for instrumented tests.
 * Add Espresso Idle Resource handling. See [IdlingColorDao] for the other part.
 *
 * When the color list changes, we notify Espresso to allow the UI test to continue
 */
class TestColorListObserver(private val idlingRes: CountingIdlingResource) : ColorListObserver() {

    override fun executeAfterChange() {
        Thread.sleep(30) //Let the recycler view time to update before decrementing
        idlingRes.decrement()
    }
}