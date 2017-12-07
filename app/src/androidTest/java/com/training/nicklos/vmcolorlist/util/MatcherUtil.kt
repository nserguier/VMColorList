package com.training.nicklos.vmcolorlist.util

import android.graphics.drawable.ColorDrawable
import android.view.View
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher


/**
 * Contains helper methods for custom Matcher's
 */
object MatcherUtil {

    fun withBackgroundColor(color: Int): Matcher<View> {
        return object : BaseMatcher<View>() {

            override fun matches(item: Any?): Boolean {
                val view = item as View
                val background = view.background
                return if (background is ColorDrawable) {
                    color == background.color
                } else false
            }

            override fun describeTo(description: Description) {
                description.appendText("with text color: ")
            }
        }
    }
}