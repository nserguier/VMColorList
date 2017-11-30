package com.training.nicklos.vmcolorlist.ui.coloredit

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.training.nicklos.vmcolorlist.R
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.ui.BaseFragment
import com.training.nicklos.vmcolorlist.util.Constants.CODE_TRANSITION_NAME
import com.training.nicklos.vmcolorlist.util.Constants.COLOR_TRANSITION_NAME
import com.training.nicklos.vmcolorlist.util.Constants.EXTRA_COLOR_ID
import com.training.nicklos.vmcolorlist.viewmodel.ColorEditViewModel
import kotlinx.android.synthetic.main.fragment_color_edit.*

/**
 * Fragment to allow the user to edit the selected color
 * by changing each RGB component.
 */
class ColorEditFragment : BaseFragment<ColorEditViewModel>(), SeekBar.OnSeekBarChangeListener {

    private lateinit var colorObserver: Observer<Color>

    override fun getViewModel() = ColorEditViewModel::class.java

    override fun getLayoutRes() = R.layout.fragment_color_edit

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set listener for edit button
        edit_button.setOnClickListener {
            viewModel.saveColor()
            activity.onBackPressed()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Set shared elements transition names
        val colorId = arguments.getLong(EXTRA_COLOR_ID, 0)
        color_preview.transitionName = "$COLOR_TRANSITION_NAME$colorId"
        color_code.transitionName = "$CODE_TRANSITION_NAME$colorId"

        //Set the viewmodel color ID
        viewModel.setColorId(colorId)

        //When the color changes for the first time, update the UI then change observer
        colorObserver = Observer { color ->
            color?.let {
                updateColor(it)
                updateSeekers(it)
                reObserveColor()
            }
        }
        viewModel.getColor().observe(this, colorObserver)
    }

    private fun updateColor(color: Color) {
        color_preview.setBackgroundColor(color.getColorValue())
        color_code.text = color.getHexCode()
    }

    private fun updateSeekers(color: Color) {
        red_seekbar.progress = color.red
        green_seekbar.progress = color.green
        blue_seekbar.progress = color.blue
    }

    /**
     * Re-observe on color changes to only update the color preview, not the seekers
     * Also set the seeker listeners
     * This is to avoid onProgressChanged being called on the initial color update, causing loops
     */
    private fun reObserveColor() {
        //Remove previous observer
        viewModel.getColor().removeObserver(colorObserver)

        setSeekerListeners()
        val newColorObserver = Observer<Color> { color ->
            color?.let { updateColor(it) }
        }
        viewModel.getColor().observe(this, newColorObserver)
    }

    private fun setSeekerListeners() {
        red_seekbar.setOnSeekBarChangeListener(this)
        green_seekbar.setOnSeekBarChangeListener(this)
        blue_seekbar.setOnSeekBarChangeListener(this)
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        viewModel.onColorChanged(red_seekbar.progress, green_seekbar.progress, blue_seekbar.progress)
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        //No implementation needed
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        //No implementation needed
    }
}
