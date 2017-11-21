package com.training.nicklos.vmcolorlist.ui.colorlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.training.nicklos.vmcolorlist.R
import com.training.nicklos.vmcolorlist.model.Color
import kotlinx.android.synthetic.main.color_list_row.view.*

/**
 * Adapter for the color list recycler view.
 * Populate recycler with rows showing a color preview and its hex code.
 */
class ColorListRecyclerAdapter(private var mColors: List<Color>,
                               private val mItemListener: (Int) -> Unit)
    : RecyclerView.Adapter<ColorListRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.color_list_row))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(mColors[position], position, mItemListener)

    override fun getItemCount() = mColors.size

    fun replaceData(colors: List<Color>) {
        mColors = colors
        notifyDataSetChanged()
    }

    class ViewHolder(private val rowView: View) : RecyclerView.ViewHolder(rowView) {
        fun bind(color: Color, index: Int, onClick: (Int) -> Unit) = with(rowView) {
            color_preview.setBackgroundColor(color.getColorValue())
            color_code.text = color.getHexCode()

            rowView.setOnClickListener { onClick(index) }
        }
    }
}

fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)
