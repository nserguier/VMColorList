package com.training.nicklos.vmcolorlist.ui.colorlist

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.training.nicklos.vmcolorlist.R
import com.training.nicklos.vmcolorlist.ui.coloredit.ColorEditRobot
import com.training.nicklos.vmcolorlist.util.RecyclerViewItemCountAssertion
import com.training.nicklos.vmcolorlist.util.ViewActionUtil

/**
 * Following the robot pattern for UI testing,
 * this class contains the UI interaction logic using Espresso.
 * Provides high level methods to use in the actual tests.
 *
 * It contains the HOW: how to perform elementary operations on the [ColorListFragment]
 * as well as some useful checks on the UI.
 */
class ColorListRobot {

    companion object {
        /**
         * Helper method to execute code on robot by bloc and replace the Builder pattern
         */
        fun colorList(func: ColorListRobot.() -> Unit) = ColorListRobot().apply(func)
    }

    fun addRandomColor() {
        onView(withId(R.id.add_color_fab)).perform(click())
    }

    fun clickColor(position: Int, func: ColorEditRobot.() -> Unit): ColorEditRobot {
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
}

