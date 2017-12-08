package com.training.nicklos.vmcolorlist.util

import android.support.v7.widget.RecyclerView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.view.View
import junit.framework.Assert


/**
 * Helper assertion to check a recycler view's item count
 */
class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        noViewFoundException?.let { throw it }
        Assert.assertEquals(expectedCount, (view as RecyclerView).adapter.itemCount)
    }
}