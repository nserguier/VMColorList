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
 */
class ColorListRobot {

    //When true, the next check on the UI should not match
    private var not = false

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

    fun isColorAt(position: Int, color: Int) {
        onView(withRecyclerView(R.id.color_list_recycler)
                .atPositionOnView(position, R.id.color_preview))
                .check(mayMatch(MatcherUtil.withBackgroundColor(color)))
    }

    fun isHexCodeAt(position: Int, hex: String) {
        onView(withRecyclerView(R.id.color_list_recycler)
                .atPositionOnView(position, R.id.color_code))
                .check(mayMatch(withText(hex)))
    }

    private fun mayMatch(viewMatcher: Matcher<in View>): ViewAssertion {
        return if (not) {
            not = false //Reset not to false for the next check
            matches(not(viewMatcher))
        } else {
            matches(viewMatcher)
        }
    }

    fun not() = this.apply { not = true }
}

