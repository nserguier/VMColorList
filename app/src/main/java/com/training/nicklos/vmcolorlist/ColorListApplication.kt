package com.training.nicklos.vmcolorlist

import android.app.Application
import com.training.nicklos.vmcolorlist.di.AppComponent
import com.training.nicklos.vmcolorlist.di.AppModule
import com.training.nicklos.vmcolorlist.di.DaggerAppComponent

/**
 * Created by nicklos on 11/17/17.
 */
class ColorListApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}