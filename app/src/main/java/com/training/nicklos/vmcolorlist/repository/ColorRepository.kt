package com.training.nicklos.vmcolorlist.repository

import android.arch.lifecycle.LiveData
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.db.ColorDao
import java.util.*

/**
 * Mediator between sources of data
 * allows to abstract the data sources from the rest of the app.
 */
class ColorRepository(var colorDao: ColorDao) {

    private val COLOR_MAX = 255

    fun addRandomColor() {
        val randColor = Color(Random().nextInt(COLOR_MAX),
                Random().nextInt(COLOR_MAX),
                Random().nextInt(COLOR_MAX))
        colorDao.insertColor(randColor)
    }

    fun getColors(): LiveData<List<Color>> {
        return colorDao.getAllColors()
    }
}