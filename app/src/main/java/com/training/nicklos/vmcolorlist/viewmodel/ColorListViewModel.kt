package com.training.nicklos.vmcolorlist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.repository.ColorRepository
import javax.inject.Inject

/**
 * ViewModel for the [ColorListFragment]
 * Holds the data and logic to feed to the fragment.
 */
class ColorListViewModel @Inject constructor(private var colorRepo: ColorRepository) : ViewModel() {

    var colors: LiveData<List<Color>>? = null

    init {
        if (colors == null) {
            colors = colorRepo.getColors()
        }
    }

    fun addColor() {
        colorRepo.addRandomColor()
    }

    fun deleteColor(color: Color) {
        colorRepo.deleteColor(color)
    }
}