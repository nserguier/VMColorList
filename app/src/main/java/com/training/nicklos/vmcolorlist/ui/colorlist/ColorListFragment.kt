package com.training.nicklos.vmcolorlist.ui.colorlist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.training.nicklos.vmcolorlist.ColorListApplication
import com.training.nicklos.vmcolorlist.R
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.ui.coloredit.ColorEditActivity
import com.training.nicklos.vmcolorlist.util.Constants.CODE_TRANSITION_NAME
import com.training.nicklos.vmcolorlist.util.Constants.COLOR_TRANSITION_NAME
import com.training.nicklos.vmcolorlist.util.Constants.EXTRA_COLOR_ID
import com.training.nicklos.vmcolorlist.viewmodel.ColorListViewModel
import kotlinx.android.synthetic.main.color_list_row.view.*
import kotlinx.android.synthetic.main.fragment_color_list.*
import javax.inject.Inject

/**
 * Fragment to show the list of colors.
 * This is a UI controller - only displays data provided in the VM
 */
class ColorListFragment : Fragment() {

    private lateinit var viewModel: ColorListViewModel
    private lateinit var mAdapter: ColorAdapter

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_color_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity.application as ColorListApplication).appComponent.inject(this)

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

        // Get the viewmodel and observe on changes
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ColorListViewModel::class.java)

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
