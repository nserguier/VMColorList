package com.training.nicklos.vmcolorlist.ui.colorlist

import android.support.test.espresso.IdlingRegistry
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.training.nicklos.vmcolorlist.di.MyCountingIdlingResource
import com.training.nicklos.vmcolorlist.ui.colorlist.ColorListRobot.Companion.colorList
import org.junit.Before
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

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(MyCountingIdlingResource.instance)
    }

    @Test
    fun testAddColors() {
        colorList {
            addColors(4)
            isListCount(4)
        }
    }

    @Test
    fun testDeleteColors() {
        colorList {
            addColors(3)
            deleteColor(0)
            isListCount(2)
            deleteColor(1)
            isListCount(1)
        }
    }

}