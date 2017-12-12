package com.training.nicklos.vmcolorlist.di

import android.arch.paging.LivePagedListProvider
import com.training.nicklos.vmcolorlist.db.ColorDao
import com.training.nicklos.vmcolorlist.db.ColorDatabase
import com.training.nicklos.vmcolorlist.model.Color

/**
 * Decorated version of [com.training.nicklos.vmcolorlist.db.ColorDao]
 * to add Espresso Idle Resource handling. See [ColorListObserver] for the other part.
 *
 * This helps Espresso UI tests know when a DAO action is running, so the test should wait for it to finish.
 */
class IdlingColorDao(database: ColorDatabase) : ColorDao {

    private val realDao = database.colorDao()

    override fun getAllColors(): LivePagedListProvider<Int, Color> {
        MyCountingIdlingResource.instance.increment()
        return realDao.getAllColors()
    }

    override fun findColorById(id: Long): Color {
        return realDao.findColorById(id)
    }

    override fun insertColor(color: Color) {
        MyCountingIdlingResource.instance.increment()
        realDao.insertColor(color)
    }

    override fun updateColor(color: Color) {
        MyCountingIdlingResource.instance.increment()
        realDao.updateColor(color)
    }

    override fun deleteColor(color: Color) {
        MyCountingIdlingResource.instance.increment()
        realDao.deleteColor(color)
    }
}