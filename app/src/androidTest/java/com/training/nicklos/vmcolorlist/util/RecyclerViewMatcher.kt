package com.training.nicklos.vmcolorlist.util

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


/**
 * Helper matcher to check statements on recycler row's subviews
 */
class RecyclerViewMatcher(private val recyclerViewId: Int) {

    fun atPosition(position: Int): Matcher<View> {
        return atPositionOnView(position, -1)
    }

    fun atPositionOnView(position: Int, targetViewId: Int) = object : TypeSafeMatcher<View>() {
        internal var resources: Resources? = null
        internal var childView: View? = null

        override fun describeTo(description: Description) {
            var idDescription = Integer.toString(recyclerViewId)
            if (this.resources != null) {
                idDescription = try {
                    this.resources!!.getResourceName(recyclerViewId)
                } catch (var4: Resources.NotFoundException) {
                    String.format("%s (resource name not found)", Integer.valueOf(recyclerViewId))
                }

            }

            description.appendText("with id: " + idDescription)
        }

        override fun matchesSafely(view: View): Boolean {

            this.resources = view.resources

            if (childView == null) {
                val recyclerView: RecyclerView = view.rootView.findViewById(recyclerViewId)
                if (recyclerView.id == recyclerViewId) {
                    childView = recyclerView.findViewHolderForAdapterPosition(position).itemView
                } else {
                    return false
                }
            }

            return if (targetViewId == -1) {
                view === childView
            } else {
                val targetView: View = childView!!.findViewById(targetViewId)
                view === targetView
            }

        }
    }
}