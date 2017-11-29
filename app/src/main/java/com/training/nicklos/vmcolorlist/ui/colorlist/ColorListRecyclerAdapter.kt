package com.training.nicklos.vmcolorlist.ui.colorlist

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.training.nicklos.vmcolorlist.R
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.util.Constants.COLOR_TRANSITION_NAME
import kotlinx.android.synthetic.main.color_list_row.view.*

/**
 * Adapter for the color list recycler view.
 * Populate recycler with rows showing a color preview and its hex code.
 */
class ColorListRecyclerAdapter(private var mColors: List<Color>,
                               private val mItemListener: (Long, View) -> Unit,
                               private val mDeleteListener: (Color) -> Unit)
    : RecyclerView.Adapter<ColorListRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.color_list_row))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(mColors[position], mItemListener, mDeleteListener)

    override fun getItemCount() = mColors.size

    fun replaceData(colors: List<Color>) {
        mColors = colors
        notifyDataSetChanged()
    }

    class ViewHolder(private val rowView: View) : RecyclerView.ViewHolder(rowView) {
        fun bind(color: Color, onClick: (Long, View) -> Unit, onDelete: (Color) -> Unit) = with(rowView) {
            color_preview.setBackgroundColor(color.getColorValue())
            color_code.text = color.getHexCode()

            ViewCompat.setTransitionName(color_preview, "$COLOR_TRANSITION_NAME${color.id}")
            rowView.setOnClickListener { onClick(color.id, color_preview) }
            delete_button.setOnClickListener { onDelete(color) }
        }
    }
}

fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)
