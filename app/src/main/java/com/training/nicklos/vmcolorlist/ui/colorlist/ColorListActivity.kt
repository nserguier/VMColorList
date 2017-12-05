package com.training.nicklos.vmcolorlist.ui.colorlist

import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import android.widget.FrameLayout
import com.training.nicklos.vmcolorlist.R
import com.training.nicklos.vmcolorlist.ui.BaseActivity
import com.training.nicklos.vmcolorlist.ui.coloredit.ColorEditActivity
import com.training.nicklos.vmcolorlist.ui.coloredit.ColorEditFragment
import com.training.nicklos.vmcolorlist.util.Constants
import com.training.nicklos.vmcolorlist.util.addFragmentToActivity
import kotlinx.android.synthetic.main.color_list_row.view.*

/**
 * This activity will host the [ColorListFragment] to show the list of colors
 * as well as the [ColorEditFragment] to edit the color (if the device is large enough)
 */
class ColorListActivity : BaseActivity(), OnColorItemSelectedListener {

    override fun getLayoutRes() = R.layout.activity_color_list

    override fun onColorItemSelected(colorId: Long, clickedRow: View) {
        val editFrame = findViewById<FrameLayout>(R.id.edit_frag)
        val dualPane = editFrame != null && editFrame.visibility == View.VISIBLE

        if(dualPane) {
            //We can show the edit fragment in the same activity, check for existing edit fragment
            var editFrag = supportFragmentManager.findFragmentById(R.id.edit_frag) as? ColorEditFragment
            if(editFrag == null) {
                //Make new fragment with color ID
                editFrag = ColorEditFragment.newInstance(colorId)
                addFragmentToActivity(supportFragmentManager, editFrag, R.id.edit_frag)
            } else {
                //Update existing fragment with color ID
                editFrag.updateContent(colorId, resetColor = true)
            }
        } else {
            //Otherwise we need to launch a new activity
            startEditActivity(colorId, clickedRow)
        }
    }

    private fun startEditActivity(colorId: Long, clickedRow: View) {
        val intent = Intent(this, ColorEditActivity::class.java)
        intent.putExtra(Constants.EXTRA_COLOR_ID, colorId)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                Pair<View, String>(clickedRow.color_preview, "${Constants.COLOR_TRANSITION_NAME}$colorId"),
                Pair<View, String>(clickedRow.color_code, "${Constants.CODE_TRANSITION_NAME}$colorId")
        )
        startActivity(intent, options.toBundle())
    }

}
