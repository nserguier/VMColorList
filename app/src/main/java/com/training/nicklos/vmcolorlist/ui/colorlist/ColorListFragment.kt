package com.training.nicklos.vmcolorlist.ui.colorlist

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.training.nicklos.vmcolorlist.R
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.ui.BaseFragment
import com.training.nicklos.vmcolorlist.viewmodel.ColorListViewModel
import kotlinx.android.synthetic.main.fragment_color_list.*

/**
 * Fragment to show the list of colors.
 * This is a UI controller - only displays data provided in the VM
 */
class ColorListFragment : BaseFragment<ColorListViewModel>(), ColorItemClickListener {

    private lateinit var mListener: OnColorItemSelectedListener
    private val mAdapter: ColorAdapter by lazy { ColorAdapter(this) }

    override fun getViewModel() = ColorListViewModel::class.java

    override fun getLayoutRes() = R.layout.fragment_color_list

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mListener = activity as OnColorItemSelectedListener
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

    override fun onColorItemClicked(colorId: Long, clickedRow: View) {
        //Pass the click event to the activity to handle
        mListener.onColorItemSelected(colorId, clickedRow)
    }

    override fun onColorDeleteClicked(color: Color) {
        viewModel.deleteColor(color)
    }
}
