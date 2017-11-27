package com.training.nicklos.vmcolorlist.ui.coloredit

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
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
class ColorEditFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private lateinit var viewModel: ColorEditViewModel

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity.application as ColorListApplication).appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_color_edit, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set listener on the sliders
        red_seekbar.setOnSeekBarChangeListener(this)
        green_seekbar.setOnSeekBarChangeListener(this)
        blue_seekbar.setOnSeekBarChangeListener(this)

        edit_button.setOnClickListener { activity.onBackPressed() }
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

    override fun onStartTrackingTouch(p0: SeekBar?) {
        //No implementation needed
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        //No implementation needed
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        viewModel.onColorChanged(red_seekbar.progress, green_seekbar.progress, blue_seekbar.progress)
    }
}
