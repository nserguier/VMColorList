package com.training.nicklos.vmcolorlist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.repository.ColorRepository
import javax.inject.Inject

/**
 * ViewModel for the [ColorEditFragment]
 * Holds the data and logic to feed to the fragment.
 */
class ColorEditViewModel @Inject constructor(private val colorRepo: ColorRepository) : ViewModel() {

    private var colorId: Long? = null
    private var color: MutableLiveData<Color>? = null

    /** Use this from the fragment to set the ID */
    fun setColorId(id: Long) {
        colorId = id
    }

    /**
     * Return color LiveData for the Fragment to observe
     * not MutableLiveData to prevent fragment from changing data
     */
    fun getColor(): LiveData<Color> {
        if (color == null) {
            //Initialize the live data and start the get color process
            color = MutableLiveData()
            loadColor()
        }
        return color!!
    }

    /** Attempt to load the color from the [colorId] in a different thread */
    private fun loadColor() {
        AsyncTask.execute {
            colorId?.let {
                //Use postValue since we are on a background thread
                color?.postValue(colorRepo.getColorById(it))
            }
        }
    }

    /** When the color components have been changed by the user, update color */
    fun onColorChanged(newRed: Int, newGreen: Int, newBlue: Int) {
        color?.value = color?.value?.update(newRed, newGreen, newBlue)
    }

    fun saveColor() {
        color?.value?.let { colorRepo.updateColor(it) }
    }
}