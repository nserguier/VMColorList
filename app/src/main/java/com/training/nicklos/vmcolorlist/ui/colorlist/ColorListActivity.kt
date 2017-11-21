package com.training.nicklos.vmcolorlist.ui.colorlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.training.nicklos.vmcolorlist.R
import com.training.nicklos.vmcolorlist.util.addFragmentToActivity
import kotlinx.android.synthetic.main.activity_color_list.*

/**
 * This activity will host the [ColorListFragment] to show the list of colors
 */
class ColorListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_list)
        setSupportActionBar(toolbar)

        var colorListFrag: ColorListFragment? =
                supportFragmentManager.findFragmentById(R.id.contentFrame) as ColorListFragment?
        if (colorListFrag == null) {
            // Create the fragment
            colorListFrag = ColorListFragment()
            addFragmentToActivity(supportFragmentManager, colorListFrag, R.id.contentFrame)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_color_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
