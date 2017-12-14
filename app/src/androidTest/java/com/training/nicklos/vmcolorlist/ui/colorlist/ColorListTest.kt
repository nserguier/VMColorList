package com.training.nicklos.vmcolorlist.ui.colorlist

import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.idling.CountingIdlingResource
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.training.nicklos.vmcolorlist.TestColorListApp
import com.training.nicklos.vmcolorlist.ui.colorlist.ColorListRobot.Companion.colorList
import com.training.nicklos.vmcolorlist.util.TestUtil
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


/**
 * Espresso UI tests for the whole app.
 * Needs to run on a device. Use the mock flavor to have in memory database.
 *
 * Switch to mock flavor to run this test
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ColorListTest {

    @Inject
    lateinit var idlingRes: CountingIdlingResource

    private val testColor = TestUtil.createColor(1)
    private val testColor2 = TestUtil.createColor(2)

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<ColorListActivity>(ColorListActivity::class.java)

    @Before
    fun setup() {
        TestColorListApp.component.inject(this)
        IdlingRegistry.getInstance().register(idlingRes)
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

    @Test
    fun testEditRGB() {
        colorList {
            addColor()
        }.clickColorAt(0) {
            rgb(testColor)
            //The color should be updated with new RGB
            isColorShown(testColor)
        }
    }

    @Test
    fun testSaveColor() {
        colorList {
            addColor()
        }.clickColorAt(0) {
            rgb(testColor)
        } save {
            //The changes should be saved on the color
            isColorAt(0, testColor)
        }
    }

    @Test
    fun testEditNoSaveColor() {
        colorList {
            addColor()
        }.clickColorAt(0) {
            rgb(testColor)
        } goBack {
            //The changes should not be saved on the color
            not().isColorAt(0, testColor)
        }
    }

    @Test
    fun testConsistentOnListRotation() {
        colorList {
            addColors(2)
            setOrientationLandscape()
            isListCount(2)

            addColors(2)
            setOrientationPortrait()
            isListCount(4)
        }
    }

    @Test
    fun testConsistentOnEditRotation() {
        colorList {
            addColor()
        }.clickColorAt(0) {
            rgb(testColor)
            setOrientationLandscape()
            //The color should keep the edited value after rotation
            isColorShown(testColor)

            rgb(testColor2)
            setOrientationPortrait()
            isColorShown(testColor2)
        }
    }
}