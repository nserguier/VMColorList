package com.training.nicklos.vmcolorlist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.support.annotation.MainThread
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.repository.ColorRepository
import com.training.nicklos.vmcolorlist.util.async
import javax.inject.Inject

/**
 * ViewModel for the [ColorListFragment]
 * Holds the data and logic to feed to the fragment.
 */
@MainThread
class ColorListViewModel @Inject constructor(private val colorRepo: ColorRepository) : ViewModel() {

    val colors: LiveData<PagedList<Color>> by lazy {
        colorRepo.getColors().create(
                0,
                PagedList.Config.Builder()
                        .setPageSize(80)
                        .setPrefetchDistance(80)
                        .build()
        )
    }

    fun addColor() {
        async { colorRepo.addRandomColor() }
    }

    fun deleteColor(color: Color) {
        async { colorRepo.deleteColor(color) }
    }
}