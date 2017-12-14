package com.training.nicklos.vmcolorlist

import android.support.test.espresso.idling.CountingIdlingResource


object MyCountingIdlingResource {
    val instance = CountingIdlingResource("colorList")
}