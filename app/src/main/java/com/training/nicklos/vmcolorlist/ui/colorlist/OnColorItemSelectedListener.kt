package com.training.nicklos.vmcolorlist.ui.colorlist

import android.view.View

/**
 * Interface to abstract color list item selected event, between [ColorListFragment] and [ColorListActivity]
 */
interface OnColorItemSelectedListener {

    fun onColorItemSelected(colorId: Long, clickedRow: View)

}