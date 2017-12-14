package com.training.nicklos.vmcolorlist.ui

import android.support.test.espresso.idling.CountingIdlingResource
import android.support.test.uiautomator.UiDevice
import com.training.nicklos.vmcolorlist.TestColorListApp
import javax.inject.Inject

/**
 * Superclass for UI testing robots able to perform screen rotation (landscape/portrait)
 *
 * @param usesIdleRes true if the screen uses idle resources to notify the UI of changes
 */
abstract class ScreenRotatingRobot(private val usesIdleRes: Boolean) {

    @Inject
    lateinit var idlingRes: CountingIdlingResource

    @Inject
    lateinit var device: UiDevice

    init {
        TestColorListApp.component.inject(this)
    }

    fun setOrientationLandscape() {
        //increment will be consumed by the list update (onResume)
        if (usesIdleRes) idlingRes.increment()
        device.setOrientationRight()
    }

    fun setOrientationPortrait() {
        //increment will be consumed by the list update (onResume)
        if (usesIdleRes) idlingRes.increment()
        device.setOrientationNatural()
    }
}