package com.training.nicklos.vmcolorlist.di

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import com.training.nicklos.vmcolorlist.model.Color

/**
 * In prod, we use a type alias because we want the default implementation
 * See mock implementation to understand why this is useful.
 */
typealias ColorListObserver = Observer<PagedList<Color>>
