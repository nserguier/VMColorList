package com.training.nicklos.vmcolorlist.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.training.nicklos.vmcolorlist.db.ColorDao
import com.training.nicklos.vmcolorlist.model.Color
import java.util.*

/**
 * Mediator between sources of data
 * allows to abstract the data sources from the rest of the app.
 */
class ColorRepository(private val colorDao: ColorDao) {

    private val COLOR_MAX = 255

    @WorkerThread
    fun addRandomColor() {
        val randColor = Color(Random().nextInt(COLOR_MAX),
                Random().nextInt(COLOR_MAX),
                Random().nextInt(COLOR_MAX))
        colorDao.insertColor(randColor)
    }

    /** Can be called from [MainThread] because returns [LiveData]  */
    @MainThread
    fun getColors(): DataSource.Factory<Int, Color> {
        return colorDao.getAllColors()
    }

    @WorkerThread
    fun getColorById(id: Long): Color {
        return colorDao.findColorById(id)
    }

    @WorkerThread
    fun updateColor(color: Color) {
        colorDao.updateColor(color)
    }

    @WorkerThread
    fun deleteColor(color: Color) {
        colorDao.deleteColor(color)
    }
}