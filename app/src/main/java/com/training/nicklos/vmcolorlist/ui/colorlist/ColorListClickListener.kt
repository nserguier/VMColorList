package com.training.nicklos.vmcolorlist.ui.colorlist

import android.view.View
import com.training.nicklos.vmcolorlist.model.Color

/**
 * Interface to abstract click events between the [ColorAdapter] and the [ColorListFragment]
 */
interface ColorListClickListener {

    fun onColorClicked(colorId: Long, clickedRow: View)

    fun onColorDeleteClicked(color: Color)
}