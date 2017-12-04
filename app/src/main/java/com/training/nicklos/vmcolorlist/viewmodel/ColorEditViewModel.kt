package com.training.nicklos.vmcolorlist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.MainThread
import com.training.nicklos.vmcolorlist.AppExecutors
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.repository.ColorRepository
import javax.inject.Inject

/**
 * ViewModel for the [ColorEditFragment]
 * Holds the data and logic to feed to the fragment.
 *
 * [MainThread]: All methods here will be called from main thread, create async as needed
 */
@MainThread
class ColorEditViewModel @Inject constructor(private val colorRepo: ColorRepository,
                                             private val executors: AppExecutors) : ViewModel() {

    var colorId: Long = -1
    private var color: MutableLiveData<Color>? = null

    /** Not MutableLiveData to prevent fragment from changing data */
    fun getColor(): LiveData<Color> {
        if (color == null) {
            color = MutableLiveData()
            loadColor()
        }
        return color!!
    }

    fun resetColor() {
        color = null
    }

    /** Attempt to load the color from the [colorId] in a different thread */
    private fun loadColor() {
        executors.diskIO.execute {
            //Use postValue since we are on a background thread
            color?.postValue(colorRepo.getColorById(colorId))
        }
    }

    /** When the color components have been changed by the user, update color */
    fun onColorChanged(newRed: Int, newGreen: Int, newBlue: Int) {
        color?.value = color?.value?.update(newRed, newGreen, newBlue)
    }

    /** Save the color to the DB, asynchronously */
    fun saveColor() {
        executors.diskIO.execute { color?.value?.let { colorRepo.updateColor(it) } }
    }
}