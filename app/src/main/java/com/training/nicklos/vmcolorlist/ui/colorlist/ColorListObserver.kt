package com.training.nicklos.vmcolorlist.ui.colorlist

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import com.training.nicklos.vmcolorlist.model.Color

/**
 * Decorated version of [Observer] to add the possibility
 * to execute a piece of code when the list of color changes.
 *
 * Here is the default implementation, no additional code is executed.
 * See test implementation to understand why this is useful.
 */
open class ColorListObserver : Observer<PagedList<Color>> {

    private lateinit var realObserver: Observer<PagedList<Color>>

    fun setOnChanged(_onChanged: (PagedList<Color>?) -> Unit) {
        realObserver = Observer { _onChanged(it) }
    }

    override fun onChanged(t: PagedList<Color>?) {
        realObserver.onChanged(t)
        executeAfterChange()
    }

    open fun executeAfterChange() {
        //No implementation needed here
    }
}