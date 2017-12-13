package com.training.nicklos.vmcolorlist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.annotation.MainThread
import com.training.nicklos.vmcolorlist.AppExecutors
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.repository.ColorRepository
import javax.inject.Inject

/**
 * ViewModel for the [ColorListFragment]
 * Holds the data and logic to feed to the fragment.
 */
@MainThread
class ColorListViewModel @Inject constructor(private val colorRepo: ColorRepository,
                                             private val executors: AppExecutors) : ViewModel() {

    val colors: LiveData<PagedList<Color>> by lazy {
        LivePagedListBuilder(colorRepo.getColors(), 80).build()
    }

    fun addColor() {
        executors.diskIO.execute { colorRepo.addRandomColor() }
    }

    fun deleteColor(color: Color) {
        executors.diskIO.execute { colorRepo.deleteColor(color) }
    }
}