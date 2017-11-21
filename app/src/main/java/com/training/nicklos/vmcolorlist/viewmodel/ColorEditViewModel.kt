package com.training.nicklos.vmcolorlist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.repository.ColorRepository
import com.training.nicklos.vmcolorlist.util.AbsentLiveData
import java.util.*
import javax.inject.Inject

/**
 * ViewModel for the [ColorEditFragment]
 * Holds the data and logic to feed to the fragment.
 */
class ColorEditViewModel @Inject constructor(private var colorRepo: ColorRepository) : ViewModel() {

    private val colorId = MutableLiveData<Long>()
    var color: LiveData<Color>? = null

    init {
        if (color == null) {
            //Get the color from the ID
            color = Transformations.switchMap(colorId) { id ->
                return@switchMap if (id == null) {
                    AbsentLiveData.create()
                } else {
                    colorRepo.getColorById(id)
                }
            }
        }
    }

    //Use this from the fragment
    fun setColorId(id: Long) {
        if (Objects.equals(colorId, id)) return
        else colorId.value = id
    }
}