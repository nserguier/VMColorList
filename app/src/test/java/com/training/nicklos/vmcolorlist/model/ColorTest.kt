package com.training.nicklos.vmcolorlist.model

import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Unit tests for the implementation of [Color]
 */
@RunWith(JUnit4::class)
class ColorTest {

    private val red = 55
    private val green = 237
    private val blue = 162
    private val hex = "#37eda2"

    private lateinit var color: Color

    @Before
    fun setup() {
        color = Color(red, green, blue)
    }

    @Test
    fun testHexCode() {
        Assert.assertEquals(hex, color.getHexCode())
    }

    @Test
    fun testUpdate() {
        color.update(red, green, 0)
        Assert.assertEquals(0, color.blue)
    }

    @Test(expected = IllegalArgumentException::class)
    fun illegalComponentLarge() {
        Color(256, 0, 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun illegalComponentNegative() {
        Color(-1, 0, 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun illegalComponentUpdate() {
        color.update(0, 256, 0)
    }
}