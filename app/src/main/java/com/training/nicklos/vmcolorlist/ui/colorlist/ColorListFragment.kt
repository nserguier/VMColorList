package com.training.nicklos.vmcolorlist.ui.colorlist

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.training.nicklos.vmcolorlist.R
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.ui.BaseFragment
import com.training.nicklos.vmcolorlist.ui.coloredit.ColorEditActivity
import com.training.nicklos.vmcolorlist.util.Constants.CODE_TRANSITION_NAME
import com.training.nicklos.vmcolorlist.util.Constants.COLOR_TRANSITION_NAME
import com.training.nicklos.vmcolorlist.util.Constants.EXTRA_COLOR_ID
import com.training.nicklos.vmcolorlist.viewmodel.ColorListViewModel
import kotlinx.android.synthetic.main.color_list_row.view.*
import kotlinx.android.synthetic.main.fragment_color_list.*

/**
 * Fragment to show the list of colors.
 * This is a UI controller - only displays data provided in the VM
 */
class ColorListFragment : BaseFragment<ColorListViewModel>() {

    private lateinit var mAdapter: ColorAdapter

    override fun getViewModel() = ColorListViewModel::class.java

    override fun getLayoutRes() = R.layout.fragment_color_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val itemClick: ((Long, View) -> Unit) = { colorId, rowView -> showColorEditActivity(colorId, rowView) }
        val deleteClick: ((Color) -> Unit) = { color -> viewModel.deleteColor(color) }

        mAdapter = ColorAdapter(itemClick, deleteClick)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init the recycler view
        color_list_recycler.setHasFixedSize(true)
        color_list_recycler.layoutManager = LinearLayoutManager(context)
        color_list_recycler.adapter = mAdapter

        // init the fab
        add_color_fab.setOnClickListener {
            viewModel.addColor()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Observe changes on the color list to update the UI
        val colorsObserver = Observer<PagedList<Color>> { pagedList -> mAdapter.setList(pagedList) }
        viewModel.colors.observe(this, colorsObserver)
    }

    private fun showColorEditActivity(colorId: Long, rowView: View) {
        val intent = Intent(context, ColorEditActivity::class.java)
        intent.putExtra(EXTRA_COLOR_ID, colorId)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                Pair<View, String>(rowView.color_preview, "$COLOR_TRANSITION_NAME$colorId"),
                Pair<View, String>(rowView.color_code, "$CODE_TRANSITION_NAME$colorId")
        )
        startActivity(intent, options.toBundle())
    }
}
