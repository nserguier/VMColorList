package com.training.nicklos.vmcolorlist.di

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import com.training.nicklos.vmcolorlist.model.Color

/**
 * Decorated version of [android.arch.lifecycle.Observer]
 * to add Espresso Idle Resource handling. See [IdlingColorDao] for the other part.
 *
 * This helps Espresso UI tests know when a DAO action is running, so the test should wait for it to finish.
 */
class ColorListObserver(onChanged: (PagedList<Color>?) -> Unit) : Observer<PagedList<Color>> {

    private val realObserver = Observer<PagedList<Color>> { onChanged(it) }

    override fun onChanged(t: PagedList<Color>?) {
        realObserver.onChanged(t)
        Thread.sleep(30) //Let the recycler view time to update before decrementing
        MyCountingIdlingResource.instance.decrement()
    }
}