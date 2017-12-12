package com.training.nicklos.vmcolorlist.di

import android.support.test.espresso.idling.CountingIdlingResource


object MyCountingIdlingResource {
    val instance = CountingIdlingResource("colorList")
}