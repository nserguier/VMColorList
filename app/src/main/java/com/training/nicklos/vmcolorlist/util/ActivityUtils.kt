package com.training.nicklos.vmcolorlist.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * This provides methods to help Activities load their UI.
 */


/**
 * The `fragment` is added to the container view with id `frameId`. The operation is
 * performed by the `fragmentManager`.
 *
 */
fun addFragmentToActivity(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
    checkNotNull(fragmentManager)
    checkNotNull(fragment)
    val transaction = fragmentManager.beginTransaction()
    transaction.add(frameId, fragment)
    transaction.commit()
}