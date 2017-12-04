package com.training.nicklos.vmcolorlist.ui.colorlist

import android.arch.paging.PagedList
import android.support.annotation.Size
import android.view.View
import com.training.nicklos.vmcolorlist.model.Color

/**
 * Interface to abstract color list related events, between [ColorListFragment] and [ColorListActivity]
 */
interface ColorListListener {

    fun onColorItemSelected(colorId: Long, clickedRow: View)

    fun onListFirstUpdated(@Size(min = 0) colorList: PagedList<Color>)
}