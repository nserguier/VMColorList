package com.training.nicklos.vmcolorlist.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Model class for a color. Use RGB coding.
 *
 * @param red from 0 to 255
 * @param green from 0 to 255
 * @param blue from 0 to 255
 */
@Entity
data class Color(var red: Int, var green: Int, var blue: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    init {
        checkColorParam(red)
        checkColorParam(green)
        checkColorParam(blue)
    }

    private fun checkColorParam(value: Int): Int {
        if (value in 0..255) return value
        else throw IllegalArgumentException("RGB color values should be between 0 and 255")
    }

    fun getColorValue() = android.graphics.Color.rgb(red, green, blue)

    fun getHexCode(): String {
        val redHex = Integer.toHexString(red)
        val greenHex = Integer.toHexString(green)
        val blueHex = Integer.toHexString(blue)
        return "#$redHex$greenHex$blueHex"
    }

    fun update(newRed: Int, newGreen: Int, newBlue: Int): Color {
        red = checkColorParam(newRed)
        green = checkColorParam(newGreen)
        blue = checkColorParam(newBlue)
        return this
    }
}