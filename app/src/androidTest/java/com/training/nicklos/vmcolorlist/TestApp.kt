package com.training.nicklos.vmcolorlist

import android.app.Application

/**
 * We use a separate App for tests to prevent initializing dependency injection.
 *
 * See [com.training.nicklos.vmcolorlist.util.ColorTestRunner].
 */
class TestApp : Application()