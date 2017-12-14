package com.training.nicklos.vmcolorlist.db

import android.arch.paging.DataSource
import android.support.test.espresso.idling.CountingIdlingResource
import com.training.nicklos.vmcolorlist.MyCountingIdlingResource
import com.training.nicklos.vmcolorlist.model.Color

/**
 * Decorated version of [ColorDao]
 * to add Espresso Idle Resource handling. See [ColorListObserver] for the other part.
 *
 * This helps Espresso UI tests know when a DAO action is running, so the test should wait for it to finish.
 */
class IdlingColorDao(database: ColorDatabase, private val idlingRes: CountingIdlingResource) : ColorDao {

    private val realDao = database.colorDao()

    override fun getAllColors(): DataSource.Factory<Int, Color> {
        idlingRes.increment()
        return realDao.getAllColors()
    }

    override fun findColorById(id: Long): Color {
        return realDao.findColorById(id)
    }

    override fun insertColor(color: Color) {
        idlingRes.increment()
        realDao.insertColor(color)
    }

    override fun updateColor(color: Color) {
        idlingRes.increment()
        realDao.updateColor(color)
    }

    override fun deleteColor(color: Color) {
        idlingRes.increment()
        realDao.deleteColor(color)
    }
}