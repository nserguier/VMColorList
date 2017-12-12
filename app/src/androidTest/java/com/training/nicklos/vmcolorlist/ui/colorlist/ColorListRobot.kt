package com.training.nicklos.vmcolorlist.ui.colorlist

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import com.training.nicklos.vmcolorlist.R
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.ui.ScreenRotatingRobot
import com.training.nicklos.vmcolorlist.ui.coloredit.ColorEditRobot
import com.training.nicklos.vmcolorlist.util.MatcherUtil
import com.training.nicklos.vmcolorlist.util.RecyclerViewItemCountAssertion
import com.training.nicklos.vmcolorlist.util.ViewActionUtil
import com.training.nicklos.vmcolorlist.util.ViewActionUtil.withRecyclerView
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not

/**
 * Following the robot pattern for UI testing,
 * this class contains the UI interaction logic using Espresso.
 * Provides high level methods to use in the actual tests.
 *
 * It contains the HOW: how to perform elementary operations on the [ColorListFragment]
 * as well as some useful checks on the UI.
 *
 * @param not When true, every checks on the UI should not match (be different from given value)
 */
class ColorListRobot(private val not: Boolean = false) : ScreenRotatingRobot(usesIdleRes = true) {

    companion object {
        /**
         * Helper method to execute a bloc of code on the screen the robot is associated to
         */
        fun colorList(func: ColorListRobot.() -> Unit) = ColorListRobot().apply(func)
    }

    fun addColors(count: Int) {
        repeat(count) { onView(withId(R.id.add_color_fab)).perform(click()) }
    }

    fun addColor() {
        addColors(1)
    }

    fun clickColorAt(position: Int, func: ColorEditRobot.() -> Unit): ColorEditRobot {
        onView(withId(R.id.color_list_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition<ColorAdapter.ViewHolder>(position, click()))
        //Redirect to the ColorEdit screen, so send back the ColorEditRobot
        return ColorEditRobot().apply(func)
    }

    fun deleteColor(position: Int) {
        onView(withId(R.id.color_list_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition<ColorAdapter.ViewHolder>(position,
                        ViewActionUtil.clickChildViewWithId(R.id.delete_button)))
    }

    fun isListCount(count: Int) {
        onView(withId(R.id.color_list_recycler))
                .check(RecyclerViewItemCountAssertion(count))
    }

    private fun isColorPreviewAt(position: Int, color: Int) {
        onView(withRecyclerView(R.id.color_list_recycler)
                .atPositionOnView(position, R.id.color_preview))
                .check(mayMatch(MatcherUtil.withBackgroundColor(color)))
    }

    private fun isColorHexAt(position: Int, hex: String) {
        onView(withRecyclerView(R.id.color_list_recycler)
                .atPositionOnView(position, R.id.color_code))
                .check(mayMatch(withText(hex)))
    }

    fun isColorAt(position: Int, color: Color) {
        isColorPreviewAt(position, color.getColorValue())
        isColorHexAt(position, color.getHexCode())
    }

    /** Use match or match not depending on the [not] value */
    private fun mayMatch(viewMatcher: Matcher<in View>): ViewAssertion {
        return if (not) {
            matches(not(viewMatcher))
        } else {
            matches(viewMatcher)
        }
    }

    /**
     * Return a new robot that will use mismatch UI checks
     */
    fun not() = ColorListRobot(not = true)
}

