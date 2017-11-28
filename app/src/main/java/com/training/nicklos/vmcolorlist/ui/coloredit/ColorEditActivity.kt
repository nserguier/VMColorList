package com.training.nicklos.vmcolorlist.ui.coloredit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.training.nicklos.vmcolorlist.R
import com.training.nicklos.vmcolorlist.util.Constants.EXTRA_COLOR_ID
import com.training.nicklos.vmcolorlist.util.addFragmentToActivity
import kotlinx.android.synthetic.main.activity_color_edit.*

/**
 * This activity will host the [ColorEditFragment]
 * to edit the selected color from the list
 */
class ColorEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_edit)
        setSupportActionBar(toolbar)

        //Get color ID
        val colorId = intent.getLongExtra(EXTRA_COLOR_ID, 0L)

        //Show fragment
        var colorEditFragment: ColorEditFragment? =
                supportFragmentManager.findFragmentById(R.id.contentFrame) as ColorEditFragment?
        if(colorEditFragment == null) {
            //Create bundle
            val args = Bundle()
            args.putLong(EXTRA_COLOR_ID, colorId)
            //Create the fragment with color ID argument
            colorEditFragment = ColorEditFragment().apply { arguments = args }
            addFragmentToActivity(supportFragmentManager, colorEditFragment, R.id.contentFrame)
        }
    }
}
