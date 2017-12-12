package com.training.nicklos.vmcolorlist.ui.coloredit

import android.support.annotation.IntRange
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.training.nicklos.vmcolorlist.R
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.ui.ScreenRotatingRobot
import com.training.nicklos.vmcolorlist.ui.colorlist.ColorListRobot
import com.training.nicklos.vmcolorlist.util.MatcherUtil.withBackgroundColor
import com.training.nicklos.vmcolorlist.util.ViewActionUtil

/**
 * Following the robot pattern for UI testing,
 * this class contains the UI interaction logic using Espresso.
 * Provides high level methods to use in the actual tests.
 *
 * It contains the HOW: how to perform elementary operations on the [ColorEditFragment]
 * as well as some useful checks on the UI.
 */
class ColorEditRobot : ScreenRotatingRobot(usesIdleRes = false) {

    private fun red(@IntRange(from = 0, to = 255) value: Int) {
        onView(withId(R.id.red_seekbar))
                .perform(ViewActionUtil.setSeekbarProgress(value))
    }

    private fun green(@IntRange(from = 0, to = 255) value: Int) {
        onView(withId(R.id.green_seekbar))
                .perform(ViewActionUtil.setSeekbarProgress(value))
    }

    private fun blue(@IntRange(from = 0, to = 255) value: Int) {
        onView(withId(R.id.blue_seekbar))
                .perform(ViewActionUtil.setSeekbarProgress(value))
    }

    fun rgb(color: Color) {
        red(color.red)
        green(color.green)
        blue(color.blue)
    }

    infix fun save(func: ColorListRobot.() -> Unit): ColorListRobot {
        onView(withId(R.id.save_button))
                .perform(click())
        //Redirect to the ColorList screen, so send back the ColorListRobot
        return ColorListRobot().apply(func)
    }

    infix fun goBack(func: ColorListRobot.() -> Unit): ColorListRobot {
        //Safe to press back since this is not the root activity (for handset devices at least)
        //Need to change that for tablet testing
        pressBack()
        //Redirect to the ColorList screen, so send back the ColorListRobot
        return ColorListRobot().apply(func)
    }

    private fun isColorHex(hex: String) {
        onView(withId(R.id.color_code)).check(matches(withText(hex)))
    }

    private fun isColorPreview(color: Int) {
        onView(withId(R.id.color_preview)).check(matches(withBackgroundColor(color)))
    }

    /** Combine hex code and color preview checks in one method */
    fun isColorShown(color: Color) {
        isColorHex(color.getHexCode())
        isColorPreview(color.getColorValue())
    }
}
