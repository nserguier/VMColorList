package com.training.nicklos.vmcolorlist.util

import com.training.nicklos.vmcolorlist.model.Color
import java.util.*

/**
 * Helper methods for tests
 */
object TestUtil {

    private val COLOR_MAX = 255

    fun createColor(colorId: Long) =
            Color(Random().nextInt(COLOR_MAX),
                    Random().nextInt(COLOR_MAX),
                    Random().nextInt(COLOR_MAX)).apply { id = colorId }
}