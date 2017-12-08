package com.training.nicklos.vmcolorlist.ui.colorlist

import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.training.nicklos.vmcolorlist.ui.colorlist.ColorListRobot.Companion.colorList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Espresso UI tests for the whole app.
 * Needs to run on a device. Use the mock flavor to have in memory database.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ColorListTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<ColorListActivity>(ColorListActivity::class.java)

    @Test
    fun addColor() {
        colorList {
            addRandomColor()
            addRandomColor()
            addRandomColor()
            addRandomColor()
            isListCount(4)
        }
    }
}