package com.training.nicklos.vmcolorlist.ui.colorlist

import android.arch.paging.PagedListAdapter
import android.support.v4.view.ViewCompat
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.training.nicklos.vmcolorlist.R
import com.training.nicklos.vmcolorlist.model.Color
import com.training.nicklos.vmcolorlist.util.Constants.CODE_TRANSITION_NAME
import com.training.nicklos.vmcolorlist.util.Constants.COLOR_TRANSITION_NAME
import kotlinx.android.synthetic.main.color_list_row.view.*

/**
 * Adapter for the color list recycler view.
 * Populate recycler with rows showing a color preview and its hex code.
 */
class ColorAdapter(private val colorListListener: ColorListClickListener)
    : PagedListAdapter<Color, ColorAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.color_list_row))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val color = getItem(position)
        if (color != null) {
            holder.bind(color, colorListListener)
        } else {
            //Null defines a placeholder item - PageListAdapter will automatically invalidate
            //this row when the actual object is loaded from the database
            holder.clear()
        }
    }

    class ViewHolder(private val rowView: View) : RecyclerView.ViewHolder(rowView) {
        fun bind(color: Color, colorListListener: ColorListClickListener) = with(rowView) {
            //Fill color data into views
            color_preview.setBackgroundColor(color.getColorValue())
            color_code.text = color.getHexCode()

            //Set names for the shared elements transition
            ViewCompat.setTransitionName(color_preview, "$COLOR_TRANSITION_NAME${color.id}")
            ViewCompat.setTransitionName(color_code, "$CODE_TRANSITION_NAME${color.id}")

            //Set row item click listeners
            rowView.setOnClickListener { colorListListener.onColorClicked(color.id, rowView) }
            delete_button.setOnClickListener { colorListListener.onColorDeleteClicked(color) }
        }

        fun clear() = with(rowView) {
            //Clear color data from view
            color_preview.setBackgroundColor(0)
            color_code.text = ""
        }
    }
}

/**
 * This object helps the adapter to determine the actual differences between two sets of data
 * Therefore it will animate the recyclerView items only where necessary
 */
val DIFF_CALLBACK = object : DiffCallback<Color>() {

    override fun areItemsTheSame(oldColor: Color, newColor: Color): Boolean {
        //Color components may have changes if reloaded from the DB, but ID is fixed
        return oldColor.id == newColor.id
    }

    override fun areContentsTheSame(oldColor: Color, newColor: Color): Boolean {
        //Safe to use == here because [Color] is a data class, so it will compare the RGB components
        return oldColor == newColor
    }
}

fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)
