package com.training.nicklos.vmcolorlist.ui.coloredit

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.training.nicklos.vmcolorlist.ColorListApplication
import com.training.nicklos.vmcolorlist.R
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.viewmodel.ColorEditViewModel
import kotlinx.android.synthetic.main.fragment_color_edit.*
import javax.inject.Inject

/**
 * Fragment to allow the user to edit the selected color
 * by changing each RGB component.
 */
class ColorEditFragment : Fragment() {

    private lateinit var viewModel: ColorEditViewModel

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_color_edit, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity.application as ColorListApplication).appComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Get the viewmodel and set the color ID
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ColorEditViewModel::class.java)
        viewModel.setColorId(arguments.getLong(ColorEditActivity.EXTRA_COLOR_ID, 0))

        //When the color changes, update the UI
        val colorObserver = Observer<Color> { color ->
            color?.let {
                color_preview.setBackgroundColor(it.getColorValue())
                red_seekbar.progress = it.red
                green_seekbar.progress = it.green
                blue_seekbar.progress = it.blue
            }
        }
        viewModel.color?.observe(this, colorObserver)
    }
}
