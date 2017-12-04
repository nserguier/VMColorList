package com.training.nicklos.vmcolorlist.ui.colorlist

import android.arch.paging.PagedList
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import com.training.nicklos.vmcolorlist.R
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.ui.BaseActivity
import com.training.nicklos.vmcolorlist.ui.coloredit.ColorEditActivity
import com.training.nicklos.vmcolorlist.ui.coloredit.ColorEditFragment
import com.training.nicklos.vmcolorlist.util.Constants
import kotlinx.android.synthetic.main.color_list_row.view.*

/**
 * This activity will host the [ColorListFragment] to show the list of colors
 */
class ColorListActivity : BaseActivity(), ColorListListener {

    override fun getLayoutRes() = R.layout.activity_color_list

    override fun onListFirstUpdated(colorList: PagedList<Color>) {
        //Update the color edit fragment if it is showing (for tablet devices)
        (supportFragmentManager.findFragmentById(R.id.edit_frag) as? ColorEditFragment)
                ?.updateContent(colorList[0]!!.id)
    }

    override fun onColorItemSelected(colorId: Long, clickedRow: View) {
        val editFrag = supportFragmentManager.findFragmentById(R.id.edit_frag) as? ColorEditFragment
        if (editFrag == null) {
            startEditActivity(colorId, clickedRow)
        } else {
            editFrag.updateContent(colorId, resetColor = true)
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
